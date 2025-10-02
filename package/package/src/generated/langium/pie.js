
// Generated wrapper for pie Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: 'pie',
  
  parse: function(content) {
    // Basic validation for pie diagrams
    const firstLine = content.trim().split('\n')[0].toLowerCase();
    
    if (!firstLine.includes('pie')) {
      throw new Error('Invalid pie diagram: must start with "pie"');
    }
    
    return { type: 'pie', valid: true };
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