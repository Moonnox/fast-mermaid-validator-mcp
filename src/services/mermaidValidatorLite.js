/**
 * Lightweight Mermaid Diagram Validation Service (DEPRECATED)
 * DEPRECATED: This validator uses external mermaid.js dependencies
 * Use CustomMermaidValidator instead for grammar-based validation
 */


class MermaidValidatorLite {
  constructor() {
    throw new Error('MermaidValidatorLite is deprecated. Use CustomMermaidValidator for grammar-based validation without external dependencies.');
  }

  /**
   * DEPRECATED: All methods are disabled
   * Use CustomMermaidValidator instead
   */
  static getDeprecationMessage() {
    return {
      error: 'MermaidValidatorLite is deprecated',
      message: 'This validator requires external mermaid.js dependencies which have been removed. Use CustomMermaidValidator for grammar-based validation.',
      alternative: 'CustomMermaidValidator',
      timestamp: new Date().toISOString()
    };
  }
}

module.exports = MermaidValidatorLite;