
// Generated wrapper for packet Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: 'packet',
  
  parse: function(content) {
    // Basic validation for packet diagrams
    const firstLine = content.trim().split('\n')[0].toLowerCase();
    
    if (!firstLine.includes('packet')) {
      throw new Error('Invalid packet diagram: must start with "packet"');
    }
    
    return { type: 'packet', valid: true };
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