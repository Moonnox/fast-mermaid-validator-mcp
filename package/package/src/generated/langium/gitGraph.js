
// Generated wrapper for gitGraph Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: 'gitGraph',
  
  parse: function(content) {
    // Basic validation for gitGraph diagrams
    const firstLine = content.trim().split('\n')[0].toLowerCase();
    
    if (!firstLine.includes('gitGraph')) {
      throw new Error('Invalid gitGraph diagram: must start with "gitGraph"');
    }
    
    return { type: 'gitGraph', valid: true };
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