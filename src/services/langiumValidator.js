/**
 * Langium-based Mermaid Diagram Validator
 * Handles validation for newer diagram types that use Langium parsers instead of Jison
 */

const logger = require('../utils/logger');

class LangiumValidator {
  constructor() {
    this.parsers = {};
    this.initializers = {
      pie: async () => {
        try {
          // For now, we'll use the pre-built parser from the parent repo
          const { parse } = await import('../../language/generated/parse.js');
          this.parsers.pie = { parse: (text) => parse('pie', text) };
        } catch (error) {
          // Fallback to basic validation since Langium setup is complex
          logger.warn('Langium parser not available, using basic validation for pie charts');
          this.parsers.pie = { parse: this.basicPieValidation.bind(this) };
        }
      },
      gitGraph: async () => {
        try {
          const { parse } = await import('../../language/generated/parse.js');
          this.parsers.gitGraph = { parse: (text) => parse('gitGraph', text) };
        } catch (error) {
          logger.warn('Langium parser not available, using basic validation for git diagrams');
          this.parsers.gitGraph = { parse: this.basicGitValidation.bind(this) };
        }
      },
      info: async () => {
        try {
          const { parse } = await import('../../language/generated/parse.js');
          this.parsers.info = { parse: (text) => parse('info', text) };
        } catch (error) {
          logger.warn('Langium parser not available, using basic validation for info diagrams');
          this.parsers.info = { parse: this.basicInfoValidation.bind(this) };
        }
      },
      architecture: async () => {
        try {
          const { parse } = await import('../../language/generated/parse.js');
          this.parsers.architecture = { parse: (text) => parse('architecture', text) };
        } catch (error) {
          logger.warn('Langium parser not available, using basic validation for architecture diagrams');
          this.parsers.architecture = { parse: this.basicArchitectureValidation.bind(this) };
        }
      },
      radar: async () => {
        try {
          const { parse } = await import('../../language/generated/parse.js');
          this.parsers.radar = { parse: (text) => parse('radar', text) };
        } catch (error) {
          logger.warn('Langium parser not available, using basic validation for radar charts');
          this.parsers.radar = { parse: this.basicRadarValidation.bind(this) };
        }
      },
      packet: async () => {
        try {
          const { parse } = await import('../../language/generated/parse.js');
          this.parsers.packet = { parse: (text) => parse('packet', text) };
        } catch (error) {
          logger.warn('Langium parser not available, using basic validation for packet diagrams');
          this.parsers.packet = { parse: this.basicPacketValidation.bind(this) };
        }
      },
      treemap: async () => {
        try {
          const { parse } = await import('../../language/generated/parse.js');
          this.parsers.treemap = { parse: (text) => parse('treemap', text) };
        } catch (error) {
          logger.warn('Langium parser not available, using basic validation for treemap diagrams');
          this.parsers.treemap = { parse: this.basicTreemapValidation.bind(this) };
        }
      },
      zenuml: async () => {
        try {
          // ZenUML has a separate package, use basic validation for now
          logger.warn('ZenUML parser not available, using basic validation for ZenUML diagrams');
          this.parsers.zenuml = { parse: this.basicZenUMLValidation.bind(this) };
        } catch (error) {
          logger.warn('ZenUML parser not available, using basic validation for ZenUML diagrams');
          this.parsers.zenuml = { parse: this.basicZenUMLValidation.bind(this) };
        }
      }
    };
  }

  async validateWithLangiumGrammar(content, diagramType, result) {
    try {
      const startTime = Date.now();
      
      // Initialize parser if not already done
      if (!this.parsers[diagramType]) {
        if (this.initializers[diagramType]) {
          await this.initializers[diagramType]();
        } else {
          throw new Error(`No Langium parser available for diagram type: ${diagramType}`);
        }
      }

      const parser = this.parsers[diagramType];
      if (!parser) {
        throw new Error(`Failed to initialize parser for: ${diagramType}`);
      }

      // Attempt to parse the content
      await parser.parse(content);
      
      const endTime = Date.now();
      const processingTime = endTime - startTime;

      result.valid = true;
      result.errors = [];
      result.warnings = [];
      result.metadata.validationMethod = 'langium_grammar';
      result.metadata.processingTime = processingTime;
      result.metadata.customValidator = true;

      logger.info(`Langium validation passed for ${diagramType}`, {
        diagramType,
        contentLength: content.length,
        processingTime
      });

    } catch (error) {
      result.valid = false;
      
      // Parse Langium error format
      if (error.result && (error.result.lexerErrors || error.result.parserErrors)) {
        const errors = [];
        
        if (error.result.lexerErrors) {
          error.result.lexerErrors.forEach(lexError => {
            errors.push({
              type: 'lexer_error',
              message: lexError.message,
              line: lexError.line || 1,
              column: lexError.column || 1,
              expected: null,
              found: null
            });
          });
        }
        
        if (error.result.parserErrors) {
          error.result.parserErrors.forEach(parseError => {
            errors.push({
              type: 'syntax_error',
              message: parseError.message,
              line: parseError.token?.startLine || 1,
              column: parseError.token?.startColumn || 1,
              expected: null,
              found: parseError.token?.image || null
            });
          });
        }
        
        result.errors = errors;
      } else {
        result.errors = [{
          type: 'syntax_error',
          message: error.message || 'Failed to parse diagram',
          line: 1,
          column: 1,
          expected: null,
          found: null
        }];
      }

      result.warnings = [];
      result.metadata.validationMethod = 'langium_grammar';
      result.metadata.customValidator = true;

      logger.warn(`Langium validation failed for ${diagramType}`, {
        diagramType,
        error: error.message,
        contentLength: content.length
      });
    }

    return result;
  }

  // Basic fallback validations for when Langium parsers are not available
  basicPieValidation(content) {
    if (!content.includes('pie')) {
      throw new Error('Pie diagram must start with "pie"');
    }
    if (!content.match(/"[^"]+"\s*:\s*[\d.]+/)) {
      throw new Error('Pie chart requires at least one data entry in format "Label" : Value');
    }
    return { isValid: true };
  }

  basicGitValidation(content) {
    if (!content.match(/gitGraph/i)) {
      throw new Error('Git diagram must start with "gitGraph"');
    }
    return { isValid: true };
  }

  basicInfoValidation(content) {
    if (!content.includes('info')) {
      throw new Error('Info diagram must start with "info"');
    }
    return { isValid: true };
  }

  basicArchitectureValidation(content) {
    if (!content.includes('architecture-beta')) {
      throw new Error('Architecture diagram must start with "architecture-beta"');
    }
    return { isValid: true };
  }

  basicRadarValidation(content) {
    if (!content.includes('radar')) {
      throw new Error('Radar chart must start with "radar"');
    }
    return { isValid: true };
  }

  basicPacketValidation(content) {
    if (!content.includes('packet-beta')) {
      throw new Error('Packet diagram must start with "packet-beta"');
    }
    return { isValid: true };
  }

  basicTreemapValidation(content) {
    if (!content.includes('treemap-beta')) {
      throw new Error('Treemap diagram must start with "treemap-beta"');
    }
    return { isValid: true };
  }

  basicZenUMLValidation(content) {
    if (!content.includes('zenuml')) {
      throw new Error('ZenUML diagram must start with "zenuml"');
    }
    // Basic check for participant interaction syntax (->)
    if (!content.match(/\w+\s*->\s*\w+/)) {
      throw new Error('ZenUML diagram requires participant interactions using "->" syntax');
    }
    return { isValid: true };
  }

  isLangiumDiagram(diagramType) {
    return ['pie', 'gitGraph', 'info', 'architecture', 'radar', 'packet', 'treemap', 'zenuml'].includes(diagramType);
  }
}

module.exports = LangiumValidator;