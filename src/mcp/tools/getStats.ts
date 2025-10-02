/**
 * Get Validation Stats MCP Tool
 * Provides information about supported diagram types, limits, and capabilities
 */

import { z } from "zod";
const CustomMermaidValidator = require("../../../src/services/customMermaidValidator");
const config = require("../../../src/config/config");

// Tool input schema (no parameters needed)
export const getStatsToolSchema = z.object({});

export type GetStatsInput = z.infer<typeof getStatsToolSchema>;

/**
 * Handle get validation stats tool request
 */
export async function handleGetStats(
  validator: any
) {
  try {
    const stats = {
      supportedDiagramTypes: validator.getSupportedTypes(),

      limits: {
        maxFileSize: config.upload?.maxFileSize || 1048576, // 1MB default
        maxFiles: config.upload?.maxFiles || 10,
        maxDiagramsPerFile: config.validation?.maxDiagramsPerFile || 50,
        maxTotalDiagrams: config.validation?.maxTotalDiagrams || 100,
        validationTimeout: config.validation?.timeout || 30000
      },

      features: {
        svgGeneration: false, // Disabled for security reasons
        zipSupport: true, // Full ZIP archive support
        markdownSupport: true,
        multiFileValidation: true,
        diagramGrammarParsers: true,
        mcpProtocol: true,
        stdioTransport: true,
        httpTransport: true, // HTTP streamable transport available
        sseSupport: true // Server-Sent Events available
      },

      about: {
        author: "Gregorio Elias Roecker Momm",
        grammarBased: true,
        engines: ["jison", "antlr", "langium"]
      },

      mcpInfo: {
        protocol: "Model Context Protocol",
        serverName: "mermaid-validator",
        version: "1.0.0",
        sdk: "@modelcontextprotocol/sdk",
        transports: ["stdio", "http-streamable", "sse"]
      },

      timestamp: new Date().toISOString()
    };

    return stats;

  } catch (error) {
    // Return error information
    return {
      error: "Failed to get validation statistics",
      message: (error as Error).message,
      timestamp: new Date().toISOString()
    };
  }
}