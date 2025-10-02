/**
 * Syntax Rule Loader Service
 * Loads configurable syntax rules from external files or ConfigMaps
 * Author: Gregorio Elias Roecker Momm
 */

const fs = require('fs');
const path = require('path');
const logger = require('../utils/logger');
const config = require('../config/config');

class SyntaxRuleLoader {
  constructor() {
    this.rulesCache = new Map();
    this.rulesDirectory = process.env.SYNTAX_RULES_DIR || path.join(__dirname, '../config/syntax-rules');
    this.loadAllRules();
  }

  /**
   * Load all syntax rule files into memory
   */
  loadAllRules() {
    try {
      logger.info('Loading syntax rules from:', { directory: this.rulesDirectory });

      // Load general rules
      this.loadRuleFile('general');

      // Load diagram-type-specific rules
      const diagramTypes = [
        'erDiagram', 'flowchart', 'sequenceDiagram', 'classDiagram',
        'stateDiagram', 'gantt', 'journey', 'mindmap', 'timeline',
        'requirement', 'sankey-beta', 'xychart-beta', 'kanban',
        'block', 'c4', 'quadrant', 'packet', 'architecture', 'treemap'
      ];

      diagramTypes.forEach(type => {
        this.loadRuleFile(type);
      });

      logger.info('Syntax rules loaded successfully', {
        totalRules: this.rulesCache.size,
        availableTypes: Array.from(this.rulesCache.keys())
      });

    } catch (error) {
      logger.error('Failed to load syntax rules:', error);
      throw error;
    }
  }

  /**
   * Load a specific rule file
   * @param {string} ruleType - Type of rule file to load
   */
  loadRuleFile(ruleType) {
    try {
      const filePath = path.join(this.rulesDirectory, `${ruleType}.txt`);

      if (fs.existsSync(filePath)) {
        const content = fs.readFileSync(filePath, 'utf8');
        this.rulesCache.set(ruleType, content.trim());
        logger.debug(`Loaded syntax rules for ${ruleType}`, {
          file: filePath,
          contentLength: content.length
        });
      } else {
        // Fallback to default rules if file doesn't exist
        this.rulesCache.set(ruleType, this.getDefaultRules(ruleType));
        logger.warn(`Syntax rule file not found, using defaults for ${ruleType}`, { file: filePath });
      }
    } catch (error) {
      logger.error(`Failed to load syntax rules for ${ruleType}:`, error);
      this.rulesCache.set(ruleType, this.getDefaultRules(ruleType));
    }
  }

  /**
   * Get syntax rules for a specific diagram type
   * @param {string} diagramType - Type of diagram
   * @returns {string} Syntax rules text
   */
  getRules(diagramType) {
    // Get type-specific rules
    const typeRules = this.rulesCache.get(diagramType) || this.rulesCache.get('general') || '';

    // Always include general rules
    const generalRules = this.rulesCache.get('general') || '';

    if (typeRules && typeRules !== generalRules) {
      return `${generalRules}\n\n${typeRules}`;
    }

    return generalRules;
  }

  /**
   * Reload rules from files (for hot reloading)
   */
  reloadRules() {
    this.rulesCache.clear();
    this.loadAllRules();
    logger.info('Syntax rules reloaded from files');
  }

  /**
   * Update a specific rule (for runtime updates)
   * @param {string} ruleType - Type of rule to update
   * @param {string} content - New rule content
   */
  updateRule(ruleType, content) {
    this.rulesCache.set(ruleType, content.trim());
    logger.info(`Updated syntax rules for ${ruleType}`, { contentLength: content.length });
  }

  /**
   * Get default rules if file doesn't exist
   * @param {string} ruleType - Type of rule
   * @returns {string} Default rule content
   */
  getDefaultRules(ruleType) {
    const defaults = {
      general: `GENERAL MERMAID SYNTAX RULES:
- Node IDs: Must start with letter, contain only letters/numbers/underscores
- Text with spaces: Must be quoted ["text with spaces"]
- Line breaks: Use <br/> tags, never literal breaks`,

      erDiagram: `ER DIAGRAM SYNTAX RULES:
- Declaration: erDiagram
- Entity syntax: ENTITY_NAME { datatype column_name constraints }
- Relationships: ||--|| = One to one, ||--o{ = One to many
- Constraints: PK, FK, UK`,

      flowchart: `FLOWCHART SYNTAX RULES:
- Declaration: flowchart TD
- Node shapes: [text] = Rectangle, {text} = Diamond
- Arrows: --> = Standard arrow, -->|text| = Labeled arrow`,

      default: `DIAGRAM SYNTAX RULES:
- Follow Mermaid syntax guidelines
- Use valid node identifiers
- Quote text with spaces or special characters`
    };

    return defaults[ruleType] || defaults.default;
  }

  /**
   * Get available rule types
   * @returns {Array} List of available rule types
   */
  getAvailableRuleTypes() {
    return Array.from(this.rulesCache.keys());
  }

  /**
   * Check if rules are loaded
   * @returns {boolean} True if rules are loaded
   */
  isLoaded() {
    return this.rulesCache.size > 0;
  }
}

module.exports = SyntaxRuleLoader;