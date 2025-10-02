/**
 * Validate Diagrams MCP Tool
 * Validates Mermaid diagrams using grammar parsers
 */

import { z } from "zod";
import { v4 as uuidv4 } from "uuid";
import { DiagramSchema, ValidationOptionsSchema, type Diagram, type ValidationOptions } from "../schemas/common.js";
const CustomMermaidValidator = require("../../../src/services/customMermaidValidator");
const FileProcessor = require("../../../src/services/fileProcessor");
const config = require("../../../src/config/config");
const logger = require("../../../src/utils/logger");

// Tool input schema
export const validateDiagramsToolSchema = z.object({
  diagrams: z.array(DiagramSchema).min(1, "At least one diagram is required")
    .max(config.validation?.maxTotalDiagrams || 100, `Maximum ${config.validation?.maxTotalDiagrams || 100} diagrams allowed`),
  options: ValidationOptionsSchema.optional()
});

export type ValidateDiagramsInput = z.infer<typeof validateDiagramsToolSchema>;

/**
 * Handle validate diagrams tool request
 */
export async function handleValidateDiagrams(
  params: ValidateDiagramsInput,
  validator: any,
  fileProcessor: any
) {
  const startTime = Date.now();
  const requestId = uuidv4();

  try {
    const { diagrams, options = {} } = params;

    // Validate total diagram count
    if (diagrams.length > (config.validation?.maxTotalDiagrams || 100)) {
      throw new Error(`Maximum ${config.validation?.maxTotalDiagrams || 100} diagrams allowed`);
    }

    // Process diagrams - convert to format expected by CustomMermaidValidator
    const diagramObjects = diagrams.map((diagram, index) => ({
      id: diagram.id || `diagram_${index + 1}`,
      content: diagram.content,
      type: diagram.type
    }));

    // Use existing validation service
    const validationResults = await validator.validateMultipleDiagrams(diagramObjects, {
      timeout: options.timeout || config.validation?.timeout || 30000
    });

    // Structure response according to MCP standards
    const response = {
      requestId,
      timestamp: new Date().toISOString(),
      processingTime: Date.now() - startTime,
      validator: "custom_grammar_parser",

      // Overall summary
      totalDiagrams: validationResults.totalDiagrams,
      validDiagrams: validationResults.validDiagrams,
      invalidDiagrams: validationResults.invalidDiagrams,

      // Detailed results per diagram
      results: validationResults.results.map((result: any) => ({
        id: result.id,
        content: options.includeDetails !== false ? result.content : undefined,
        isValid: result.isValid,
        diagramType: result.diagramType,
        detectedType: result.detectedType,
        errors: result.errors || [],
        warnings: result.warnings || [],
        validationTime: result.validationTime,
        parserUsed: result.parserUsed,

        // Additional metadata
        contentLength: result.content?.length || 0,
        lineCount: result.content ? result.content.split('\n').length : 0
      })),

      // Validation options used
      validationOptions: {
        timeout: options.timeout || config.validation?.timeout || 30000,
        strictMode: options.strictMode || false,
        includeDetails: options.includeDetails !== false
      }
    };

    logger.info("Validate diagrams completed", {
      requestId,
      totalDiagrams: response.totalDiagrams,
      validDiagrams: response.validDiagrams,
      invalidDiagrams: response.invalidDiagrams,
      processingTime: response.processingTime
    });

    return response;

  } catch (error) {
    logger.logError(error as Error, {
      context: "validate-diagrams",
      requestId,
      diagramCount: params.diagrams?.length
    });

    // Return structured error response
    return {
      requestId,
      timestamp: new Date().toISOString(),
      processingTime: Date.now() - startTime,
      error: "Validation failed",
      message: (error as Error).message,
      validator: "custom_grammar_parser"
    };
  }
}