
// Generated wrapper for info Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: 'info',
  
  parse: function(content) {
    // Basic validation for info diagrams
    const firstLine = content.trim().split('\n')[0].toLowerCase();
    
    if (!firstLine.includes('info')) {
      throw new Error('Invalid info diagram: must start with "info"');
    }
    
    return { type: 'info', valid: true };
  },
  
  validate: function(content) {
    try {
      this.parse(content);
      return { valid: true, errors: [] };
    } catch (error) {
      return { 
        valid: false, 
        errors: [{ message: error.message, line: 1 }]
      };
    }
  }
};