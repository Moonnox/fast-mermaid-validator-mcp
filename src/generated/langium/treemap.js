
// Generated wrapper for treemap Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: 'treemap',
  
  parse: function(content) {
    // Basic validation for treemap diagrams
    const firstLine = content.trim().split('\n')[0].toLowerCase();
    
    if (!firstLine.includes('treemap')) {
      throw new Error('Invalid treemap diagram: must start with "treemap"');
    }
    
    return { type: 'treemap', valid: true };
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