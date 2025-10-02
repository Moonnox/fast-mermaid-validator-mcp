
// Generated wrapper for architecture Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: 'architecture',
  
  parse: function(content) {
    // Basic validation for architecture diagrams
    const firstLine = content.trim().split('\n')[0].toLowerCase();
    
    if (!firstLine.includes('architecture')) {
      throw new Error('Invalid architecture diagram: must start with "architecture"');
    }
    
    return { type: 'architecture', valid: true };
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