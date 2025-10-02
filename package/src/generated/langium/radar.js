
// Generated wrapper for radar Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: 'radar',
  
  parse: function(content) {
    // Basic validation for radar diagrams
    const firstLine = content.trim().split('\n')[0].toLowerCase();
    
    if (!firstLine.includes('radar')) {
      throw new Error('Invalid radar diagram: must start with "radar"');
    }
    
    return { type: 'radar', valid: true };
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