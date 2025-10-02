/**
 * Validate Files MCP Tool
 * Validates Mermaid diagrams from file contents (supports Markdown and ZIP)
 */

import { z } from "zod";
import { v4 as uuidv4 } from "uuid";
import { FileContentSchema, ValidationOptionsSchema, type FileContent, type ValidationOptions } from "../schemas/common.js";
const CustomMermaidValidator = require("../../../src/services/customMermaidValidator");
const FileProcessor = require("../../../src/services/fileProcessor");
const config = require("../../../src/config/config");
const logger = require("../../../src/utils/logger");

// Tool input schema
export const validateFilesToolSchema = z.object({
  files: z.array(FileContentSchema).min(1, "At least one file is required")
    .max(config.upload?.maxFiles || 10, `Maximum ${config.upload?.maxFiles || 10} files allowed`),
  options: ValidationOptionsSchema.optional()
});

export type ValidateFilesInput = z.infer<typeof validateFilesToolSchema>;

/**
 * Handle validate files tool request
 */
export async function handleValidateFiles(
  params: ValidateFilesInput,
  validator: any,
  fileProcessor: any
) {
  const startTime = Date.now();
  const requestId = uuidv4();

  try {
    const { files, options = {} } = params;

    // Validate files parameter
    if (!files || !Array.isArray(files)) {
      throw new Error('Files parameter must be an array');
    }

    // Validate file count
    if (files.length > (config.upload?.maxFiles || 10)) {
      throw new Error(`Maximum ${config.upload?.maxFiles || 10} files allowed`);
    }

    // Process each file and extract diagrams
    const allDiagrams: any[] = [];
    const fileDetailsForResponse: any[] = [];

    for (const fileInfo of files) {
      const fileDetail = {
        fileName: fileInfo.fileName,
        size: Buffer.byteLength(fileInfo.content, 'utf8'),
        totalDiagrams: 0,
        validDiagrams: 0,
        invalidDiagrams: 0,
        results: [] as any[],
        errors: [] as any[]
      };

      try {
        // Decode content if base64
        let content = fileInfo.content;
        if (fileInfo.encoding === "base64") {
          content = Buffer.from(fileInfo.content, 'base64').toString('utf8');
        }

        // Check file size limit
        const sizeLimit = config.upload?.maxFileSize || 1048576; // 1MB default
        if (fileDetail.size > sizeLimit) {
          fileDetail.errors.push({
            type: 'file_too_large',
            message: `File size ${fileDetail.size} bytes exceeds limit ${sizeLimit} bytes`
          });
          fileDetailsForResponse.push(fileDetail);
          continue;
        }

        // Handle ZIP files
        if (fileInfo.fileName.endsWith('.zip') || fileInfo.mimeType === 'application/zip') {
          try {
            // Process ZIP file content using existing FileProcessor
            const zipResults = await processZipContent(fileInfo.content, fileInfo.fileName);

            for (const subFile of zipResults.subFiles || []) {
              if (subFile.error) {
                fileDetail.errors.push({
                  type: 'extraction_error',
                  fileName: subFile.fileName,
                  message: subFile.error
                });
                continue;
              }

              const diagrams = validator.extractDiagrams(subFile.content);

              if (diagrams.length > (config.validation?.maxDiagramsPerFile || 50)) {
                fileDetail.errors.push({
                  type: 'too_many_diagrams',
                  fileName: subFile.fileName,
                  message: `File contains ${diagrams.length} diagrams. Maximum ${config.validation?.maxDiagramsPerFile || 50} allowed per file.`
                });
                continue;
              }

              // Add source file information to diagrams
              diagrams.forEach((diagram: any) => {
                diagram.sourceFile = `${fileInfo.fileName}/${subFile.fileName}`;
                allDiagrams.push(diagram);
              });

              // Validate diagrams for this sub-file
              if (diagrams.length > 0) {
                const validation = await validator.validateMultipleDiagrams(diagrams, {
                  timeout: options.timeout || config.validation?.timeout || 30000
                });

                fileDetail.totalDiagrams += validation.totalDiagrams;
                fileDetail.validDiagrams += validation.validDiagrams;
                fileDetail.invalidDiagrams += validation.invalidDiagrams;
                fileDetail.results.push(...validation.results);
              }
            }
          } catch (error) {
            fileDetail.errors.push({
              type: 'zip_processing_error',
              message: (error as Error).message
            });
          }
          fileDetailsForResponse.push(fileDetail);
          continue;
        }

        // Extract diagrams from content
        const diagrams = validator.extractDiagrams(content);

        if (diagrams.length > (config.validation?.maxDiagramsPerFile || 50)) {
          fileDetail.errors.push({
            type: 'too_many_diagrams',
            message: `File contains ${diagrams.length} diagrams. Maximum ${config.validation?.maxDiagramsPerFile || 50} allowed per file.`
          });
        } else {
          // Add source file information to diagrams
          diagrams.forEach((diagram: any) => {
            diagram.sourceFile = fileInfo.fileName;
            allDiagrams.push(diagram);
          });

          // Validate diagrams for this file
          if (diagrams.length > 0) {
            const validation = await validator.validateMultipleDiagrams(diagrams, {
              timeout: options.timeout || config.validation?.timeout || 30000
            });

            fileDetail.totalDiagrams = validation.totalDiagrams;
            fileDetail.validDiagrams = validation.validDiagrams;
            fileDetail.invalidDiagrams = validation.invalidDiagrams;
            fileDetail.results = validation.results;
          }
        }

      } catch (error) {
        fileDetail.errors.push({
          type: 'processing_error',
          message: (error as Error).message
        });
        logger.logError(error as Error, {
          context: 'file_diagram_processing',
          fileName: fileInfo.fileName,
          requestId
        });
      }

      fileDetailsForResponse.push(fileDetail);
    }

    // Check total diagram limit
    if (allDiagrams.length > (config.validation?.maxTotalDiagrams || 100)) {
      throw new Error(`Total diagrams (${allDiagrams.length}) exceeds maximum allowed (${config.validation?.maxTotalDiagrams || 100})`);
    }

    // Calculate overall summary
    const totalDiagrams = fileDetailsForResponse.reduce((sum, file) => sum + file.totalDiagrams, 0);
    const validDiagrams = fileDetailsForResponse.reduce((sum, file) => sum + file.validDiagrams, 0);
    const invalidDiagrams = totalDiagrams - validDiagrams;

    const response = {
      requestId,
      timestamp: new Date().toISOString(),
      processingTime: Date.now() - startTime,
      validator: "custom_grammar_parser",

      // Overall summary
      totalFiles: files.length,
      processedFiles: fileDetailsForResponse.filter(f => f.errors.length === 0).length,
      totalDiagrams,
      validDiagrams,
      invalidDiagrams,

      // Detailed results per file
      files: fileDetailsForResponse,

      // Validation options used
      validationOptions: {
        timeout: options.timeout || config.validation?.timeout || 30000,
        strictMode: options.strictMode || false,
        includeDetails: options.includeDetails !== false
      }
    };

    logger.info("Validate files completed", {
      requestId,
      totalFiles: response.totalFiles,
      processedFiles: response.processedFiles,
      totalDiagrams: response.totalDiagrams,
      validDiagrams: response.validDiagrams,
      invalidDiagrams: response.invalidDiagrams,
      processingTime: response.processingTime
    });

    return response;

  } catch (error) {
    logger.error('File validation error', {
      error: (error as Error).message,
      context: "validate-files",
      requestId,
      fileCount: params.files?.length
    });

    // Return structured error response
    return {
      requestId,
      timestamp: new Date().toISOString(),
      processingTime: Date.now() - startTime,
      error: "File validation failed",
      message: (error as Error).message,
      validator: "custom_grammar_parser"
    };
  }

}

/**
 * Process ZIP file content from base64 data
 */
async function processZipContent(content: string, fileName: string): Promise<any> {
    const yauzl = require('yauzl');
    const fs = require('fs');
    const path = require('path');
    const os = require('os');

    return new Promise((resolve, reject) => {
      // Create temporary file for ZIP processing
      const tempDir = os.tmpdir();
      const tempFile = path.join(tempDir, `mcp-zip-${Date.now()}-${Math.random().toString(36).substr(2, 9)}.zip`);

      try {
        // Write base64 content to temporary file
        const buffer = Buffer.from(content, 'base64');
        fs.writeFileSync(tempFile, buffer);

        const subFiles: any[] = [];

        yauzl.open(tempFile, { lazyEntries: true }, (err: any, zipfile: any) => {
          if (err) {
            fs.unlinkSync(tempFile).catch(() => {}); // Cleanup
            return reject(new Error(`Failed to open ZIP file: ${err.message}`));
          }

          zipfile.readEntry();

          zipfile.on('entry', (entry: any) => {
            if (/\/$/.test(entry.fileName)) {
              // Directory entry, skip
              zipfile.readEntry();
              return;
            }

            // Only process text files
            if (!/\.(md|txt|markdown)$/i.test(entry.fileName)) {
              zipfile.readEntry();
              return;
            }

            zipfile.openReadStream(entry, (err: any, readStream: any) => {
              if (err) {
                subFiles.push({
                  fileName: entry.fileName,
                  error: `Failed to read entry: ${err.message}`
                });
                zipfile.readEntry();
                return;
              }

              let content = '';
              readStream.on('data', (chunk: any) => {
                content += chunk.toString('utf8');
              });

              readStream.on('end', () => {
                subFiles.push({
                  fileName: entry.fileName,
                  content: content,
                  size: entry.uncompressedSize
                });
                zipfile.readEntry();
              });

              readStream.on('error', (err: any) => {
                subFiles.push({
                  fileName: entry.fileName,
                  error: `Read error: ${err.message}`
                });
                zipfile.readEntry();
              });
            });
          });

          zipfile.on('end', () => {
            // Cleanup temp file
            fs.unlinkSync(tempFile).catch(() => {});
            resolve({ subFiles });
          });

          zipfile.on('error', (err: any) => {
            fs.unlinkSync(tempFile).catch(() => {}); // Cleanup
            reject(new Error(`ZIP processing error: ${err.message}`));
          });
        });

      } catch (error) {
        fs.unlinkSync(tempFile).catch(() => {}); // Cleanup
        reject(error);
      }
    });
}