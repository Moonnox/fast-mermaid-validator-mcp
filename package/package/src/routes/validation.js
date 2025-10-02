/**
 * Validation Routes
 * Endpoints for validating Mermaid diagrams using custom grammar parser
 * Author: Gregorio Elias Roecker Momm
 */

const express = require('express');
const multer = require('multer');
const path = require('path');
const { v4: uuidv4 } = require('uuid');
const config = require('../config/config');
const logger = require('../utils/logger');
const CustomMermaidValidator = require('../services/customMermaidValidator');
const FileProcessor = require('../services/fileProcessor');
const { 
  validateRequest, 
  validateDiagramsInput, 
  fileUploadSecurity,
  validateContentType 
} = require('../middleware/security');

const router = express.Router();
const validator = new CustomMermaidValidator();
const fileProcessor = new FileProcessor();

// Configure multer for file uploads
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, config.upload.tempDir);
  },
  filename: (req, file, cb) => {
    const uniqueSuffix = `${Date.now()}-${uuidv4()}`;
    const ext = path.extname(file.originalname);
    cb(null, `upload-${uniqueSuffix}${ext}`);
  }
});

const upload = multer({
  storage,
  limits: {
    fileSize: config.upload.maxFileSize,
    files: config.upload.maxFiles
  },
  fileFilter: (req, file, cb) => {
    // Allow all files - detailed validation will happen in security middleware
    // This handles cases where MIME type detection fails or is incorrect
    cb(null, true);
  }
});

/**
 * Direct diagram validation endpoint
 * POST /api/v1/validate
 */
router.post(
  '/validate',
  validateContentType(['application/json']),
  validateDiagramsInput,
  validateRequest,
  async (req, res) => {
    const startTime = Date.now();
    
    try {
      const { diagrams, options = {} } = req.body;
      
      // Validate total diagram count
      if (diagrams.length > config.validation.maxTotalDiagrams) {
        return res.status(400).json({
          error: 'Too many diagrams',
          message: `Maximum ${config.validation.maxTotalDiagrams} diagrams allowed`
        });
      }

      // Process diagrams
      const diagramObjects = fileProcessor.processDirectContent(diagrams);
      
      // Validate diagrams using custom grammar parser
      const results = await validator.validateMultipleDiagrams(diagramObjects, {
        timeout: options.timeout || config.validation.timeout
      });

      // Add request metadata
      results.requestId = uuidv4();
      results.processingTime = Date.now() - startTime;
      results.timestamp = new Date().toISOString();
      results.validator = 'custom_grammar_parser';

      res.json(results);

    } catch (error) {
      logger.logError(error, { 
        context: 'direct_validation',
        ip: req.ip,
        diagramCount: req.body?.diagrams?.length
      });

      res.status(500).json({
        error: 'Validation failed',
        message: error.message,
        requestId: uuidv4(),
        timestamp: new Date().toISOString()
      });
    }
  }
);

/**
 * File upload validation endpoint
 * POST /api/v1/upload/file
 */
router.post(
  '/upload/file',
  upload.array('file', config.upload.maxFiles),
  fileUploadSecurity,
  async (req, res) => {
    const startTime = Date.now();
    const requestId = uuidv4();
    
    try {
      const options = {
        timeout: parseInt(req.body.timeout, 10) || config.validation.timeout
      };

      // Process uploaded files
      const fileResults = await fileProcessor.processFiles(req.files);
      
      if (fileResults.errors.length > 0) {
        logger.logError(new Error('File processing errors'), {
          context: 'file_upload',
          errors: fileResults.errors,
          requestId
        });
      }

      // Extract all diagrams from processed files
      const allDiagrams = [];
      const fileDetailsForResponse = [];

      for (const fileInfo of fileResults.extractedContent) {
        const fileDetail = {
          fileName: fileInfo.fileName,
          size: fileInfo.size,
          totalDiagrams: 0,
          validDiagrams: 0,
          invalidDiagrams: 0,
          results: [],
          errors: []
        };

        try {
          if (fileInfo.content) {
            // Process single file
            const diagrams = validator.extractDiagrams(fileInfo.content);
            
            if (diagrams.length > config.validation.maxDiagramsPerFile) {
              fileDetail.errors.push({
                type: 'too_many_diagrams',
                message: `File contains ${diagrams.length} diagrams. Maximum ${config.validation.maxDiagramsPerFile} allowed per file.`
              });
            } else {
              // Add source file information to diagrams
              diagrams.forEach(diagram => {
                diagram.sourceFile = fileInfo.fileName;
                allDiagrams.push(diagram);
              });
              
              // Validate diagrams for this file
              const validation = await validator.validateMultipleDiagrams(diagrams, options);
              
              fileDetail.totalDiagrams = validation.totalDiagrams;
              fileDetail.validDiagrams = validation.validDiagrams;
              fileDetail.invalidDiagrams = validation.invalidDiagrams;
              fileDetail.results = validation.results;
            }
          } else if (fileInfo.subFiles) {
            // Process ZIP file contents
            for (const subFile of fileInfo.subFiles) {
              if (subFile.error) {
                fileDetail.errors.push({
                  type: 'extraction_error',
                  fileName: subFile.fileName,
                  message: subFile.error
                });
                continue;
              }

              const diagrams = validator.extractDiagrams(subFile.content);
              
              if (diagrams.length > config.validation.maxDiagramsPerFile) {
                fileDetail.errors.push({
                  type: 'too_many_diagrams',
                  fileName: subFile.fileName,
                  message: `File contains ${diagrams.length} diagrams. Maximum ${config.validation.maxDiagramsPerFile} allowed per file.`
                });
                continue;
              }

              // Add source file information to diagrams
              diagrams.forEach(diagram => {
                diagram.sourceFile = `${fileInfo.fileName}/${subFile.fileName}`;
                allDiagrams.push(diagram);
              });

              // Validate diagrams for this sub-file
              const validation = await validator.validateMultipleDiagrams(diagrams, options);
              
              fileDetail.totalDiagrams += validation.totalDiagrams;
              fileDetail.validDiagrams += validation.validDiagrams;
              fileDetail.invalidDiagrams += validation.invalidDiagrams;
              fileDetail.results.push(...validation.results);
            }
          }
        } catch (error) {
          fileDetail.errors.push({
            type: 'processing_error',
            message: error.message
          });
          logger.logError(error, { 
            context: 'file_diagram_processing',
            fileName: fileInfo.fileName,
            requestId
          });
        }

        fileDetailsForResponse.push(fileDetail);
      }

      // Check total diagram limit
      if (allDiagrams.length > config.validation.maxTotalDiagrams) {
        return res.status(400).json({
          error: 'Too many diagrams',
          message: `Total diagrams (${allDiagrams.length}) exceeds maximum allowed (${config.validation.maxTotalDiagrams})`,
          requestId,
          timestamp: new Date().toISOString()
        });
      }

      // Calculate overall summary
      const totalDiagrams = fileDetailsForResponse.reduce((sum, file) => sum + file.totalDiagrams, 0);
      const validDiagrams = fileDetailsForResponse.reduce((sum, file) => sum + file.validDiagrams, 0);
      const invalidDiagrams = totalDiagrams - validDiagrams;

      const response = {
        requestId,
        timestamp: new Date().toISOString(),
        processingTime: Date.now() - startTime,
        validator: 'custom_grammar_parser',
        
        // Overall summary
        totalFiles: fileResults.totalFiles,
        processedFiles: fileResults.processedFiles,
        totalDiagrams,
        validDiagrams,
        invalidDiagrams,
        
        // File processing summary
        fileProcessing: {
          totalFiles: fileResults.totalFiles,
          processedFiles: fileResults.processedFiles,
          errors: fileResults.errors,
          processingTime: fileResults.processingTime
        },
        
        // Detailed results per file
        files: fileDetailsForResponse,
        
        // Validation options used
        validationOptions: options
      };

      res.json(response);

    } catch (error) {
      logger.logError(error, { 
        context: 'file_upload_validation',
        ip: req.ip,
        fileCount: req.files?.length,
        requestId
      });

      res.status(500).json({
        error: 'File validation failed',
        message: error.message,
        requestId,
        timestamp: new Date().toISOString()
      });
    }
  }
);

/**
 * Get validation statistics
 * GET /api/v1/validate/stats
 */
router.get('/stats', async (req, res) => {
  try {
    const stats = {
      supportedDiagramTypes: validator.getSupportedTypes(),
      limits: {
        maxFileSize: config.upload.maxFileSize,
        maxFiles: config.upload.maxFiles,
        maxDiagramsPerFile: config.validation.maxDiagramsPerFile,
        maxTotalDiagrams: config.validation.maxTotalDiagrams,
        validationTimeout: config.validation.timeout
      },
      features: {
        svgGeneration: false, // Disabled
        zipSupport: true,
        markdownSupport: true,
        multiFileValidation: true,
        diagramGrammarParsers: true
      },
      validator: {
        type: 'custom_grammar_parser',
        author: 'Gregorio Elias Roecker Momm',
        dependencies: 'minimal',
        grammarBased: true
      },
      timestamp: new Date().toISOString()
    };

    res.json(stats);
  } catch (error) {
    logger.logError(error, { context: 'validation_stats' });
    res.status(500).json({
      error: 'Failed to get validation statistics',
      message: error.message
    });
  }
});

module.exports = router;