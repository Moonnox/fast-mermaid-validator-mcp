/**
 * Common Zod schemas for MCP tools
 */

import { z } from "zod";

// Base diagram schema
export const DiagramSchema = z.object({
  id: z.string().optional(),
  content: z.string().min(1, "Diagram content cannot be empty"),
  type: z.string().optional()
});

// Validation options schema
export const ValidationOptionsSchema = z.object({
  timeout: z.number().min(1000).max(60000).default(30000).optional(),
  strictMode: z.boolean().default(false).optional(),
  includeDetails: z.boolean().default(true).optional()
});

// File content schema
export const FileContentSchema = z.object({
  fileName: z.string().min(1, "File name is required"),
  content: z.string().min(1, "File content cannot be empty"),
  encoding: z.enum(["utf8", "base64"]).default("utf8").optional(),
  mimeType: z.string().optional()
});

export type Diagram = z.infer<typeof DiagramSchema>;
export type ValidationOptions = z.infer<typeof ValidationOptionsSchema>;
export type FileContent = z.infer<typeof FileContentSchema>;