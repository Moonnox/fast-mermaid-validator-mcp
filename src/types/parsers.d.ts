
// Auto-generated TypeScript definitions for Mermaid parsers
// Do not edit manually

export interface Parser {
  parse(input: string): any;
  yy: any;
}

export interface ParserMap {
  [key: string]: Parser;
}

export interface ValidationResult {
  valid: boolean;
  errors: Array<{
    line?: number;
    column?: number;
    message: string;
    type: string;
  }>;
  warnings: Array<{
    line?: number;
    column?: number;
    message: string;
    type: string;
  }>;
}

export interface DiagramMetadata {
  diagramType: string;
  validationMethod: string;
  contentLength: number;
  lineCount: number;
  customValidator: boolean;
  processingTime: number;
}
