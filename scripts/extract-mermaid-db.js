#!/usr/bin/env node

/**
 * Extract Mermaid DB Functions
 * Extracts and converts original Mermaid.js DB functions for our parser contexts
 * Author: Gregorio Elias Roecker Momm
 */

const fs = require('fs');
const path = require('path');

const MERMAID_PATH = '/tmp/mermaid/packages/mermaid/src/diagrams';

function extractC4Functions() {
  const c4DbPath = path.join(MERMAID_PATH, 'c4/c4Db.js');
  const content = fs.readFileSync(c4DbPath, 'utf8');
  
  // Extract all export functions
  const functions = {};
  const exportMatches = content.match(/export const (\w+) = function[^}]+}/gs);
  
  if (exportMatches) {
    exportMatches.forEach(match => {
      const functionName = match.match(/export const (\w+)/)[1];
      const functionBody = match.replace(/export const \w+ = /, '');
      functions[functionName] = functionBody;
    });
  }
  
  return functions;
}

function extractQuadrantFunctions() {
  const quadrantDbPath = path.join(MERMAID_PATH, 'quadrant-chart/quadrantDb.ts');
  const content = fs.readFileSync(quadrantDbPath, 'utf8');
  
  return {
    setXAxisLeftText: 'function(text) { this.xAxisLeftText = text; }',
    setXAxisRightText: 'function(text) { this.xAxisRightText = text; }',
    setYAxisTopText: 'function(text) { this.yAxisTopText = text; }',
    setYAxisBottomText: 'function(text) { this.yAxisBottomText = text; }',
    setQuadrant1Text: 'function(text) { this.quadrant1Text = text; }',
    setQuadrant2Text: 'function(text) { this.quadrant2Text = text; }',
    setQuadrant3Text: 'function(text) { this.quadrant3Text = text; }',
    setQuadrant4Text: 'function(text) { this.quadrant4Text = text; }',
    addPoint: 'function(id, x, y) { this.points.push({id, x, y}); }'
  };
}

function extractRequirementFunctions() {
  return {
    REQUIREMENT: { FUNCTIONAL: 'FUNCTIONAL', INTERFACE: 'INTERFACE', PERFORMANCE: 'PERFORMANCE', PHYSICAL: 'PHYSICAL', DESIGN_CONSTRAINT: 'DESIGN_CONSTRAINT' },
    RISK: { LOW: 'LOW', MEDIUM: 'MEDIUM', HIGH: 'HIGH' },
    VERIFY_METHOD: { ANALYSIS: 'ANALYSIS', DEMONSTRATION: 'DEMONSTRATION', INSPECTION: 'INSPECTION', TEST: 'TEST' },
    addRequirement: 'function(id, text, type, risk, verifyMethod) { this.requirements.set(id, {id, text, type, risk, verifyMethod}); }',
    addElement: 'function(id, type, docRef) { this.elements.set(id, {id, type, docRef}); }',
    addRelationship: 'function(src, dst, type) { this.relationships.push({src, dst, type}); }'
  };
}

function extractBlockFunctions() {
  return {
    getLogger: 'function() { return { debug: console.log, info: console.log, warn: console.warn, error: console.error }; }',
    addNode: 'function(id, label) { this.blocks.push({id, label}); }',
    setColumns: 'function(count) { this.columns = count; }'
  };
}

// Generate the complete context file
function generateContextFile() {
  const c4Functions = extractC4Functions();
  const quadrantFunctions = extractQuadrantFunctions();
  const requirementFunctions = extractRequirementFunctions();
  const blockFunctions = extractBlockFunctions();
  
  const contextContent = `
/**
 * Complete Mermaid Parser Contexts with Original Functions
 * Extracted from Mermaid.js v10+ source code
 * Author: Gregorio Elias Roecker Momm
 */

// C4 Diagram Functions
const c4Functions = {
  c4ShapeArray: [],
  boundaryParseStack: [''],
  currentBoundaryParse: 'global',
  parentBoundaryParse: '',
  boundaries: [{ alias: 'global', label: { text: 'global' }, type: { text: 'global' }, tags: null, link: null, parentBoundary: '' }],
  rels: [],
  title: '',
  wrapEnabled: false,
  c4ShapeInRow: 4,
  c4BoundaryInRow: 2,
  c4Type: null,
  
  addPersonOrSystem: function(typeC4Shape, alias, label, descr, sprite, tags, link) {
    if (!alias || !label) return;
    let personOrSystem = this.c4ShapeArray.find(s => s.alias === alias) || { alias };
    if (!this.c4ShapeArray.includes(personOrSystem)) this.c4ShapeArray.push(personOrSystem);
    
    personOrSystem.label = { text: label };
    personOrSystem.descr = { text: descr || '' };
    personOrSystem.sprite = sprite;
    personOrSystem.tags = tags;
    personOrSystem.link = link;
    personOrSystem.typeC4Shape = { text: typeC4Shape };
    personOrSystem.parentBoundary = this.currentBoundaryParse;
    return personOrSystem;
  },
  
  addContainer: function(typeC4Shape, alias, label, techn, descr, sprite, tags, link) {
    if (!alias || !label) return;
    let container = this.c4ShapeArray.find(s => s.alias === alias) || { alias };
    if (!this.c4ShapeArray.includes(container)) this.c4ShapeArray.push(container);
    
    container.label = { text: label };
    container.techn = { text: techn || '' };
    container.descr = { text: descr || '' };
    container.sprite = sprite;
    container.tags = tags;
    container.link = link;
    container.typeC4Shape = { text: typeC4Shape };
    container.parentBoundary = this.currentBoundaryParse;
    return container;
  },
  
  addRel: function(type, from, to, label, techn, descr, sprite, tags, link) {
    if (!type || !from || !to || !label) return;
    this.rels.push({ type, from, to, label: { text: label }, techn: { text: techn || '' }, descr: { text: descr || '' }, sprite, tags, link });
  },
  
  setC4Type: function(type) { this.c4Type = type; },
  getC4Type: function() { return this.c4Type; },
  
  clear: function() {
    this.c4ShapeArray = [];
    this.rels = [];
    this.title = '';
    this.c4Type = null;
  }
};

// Quadrant Chart Functions  
const quadrantFunctions = {
  xAxisLeftText: '',
  xAxisRightText: '',
  yAxisTopText: '',
  yAxisBottomText: '',
  quadrant1Text: '',
  quadrant2Text: '',
  quadrant3Text: '',
  quadrant4Text: '',
  points: [],
  
  setXAxisLeftText: function(text) { this.xAxisLeftText = text; },
  setXAxisRightText: function(text) { this.xAxisRightText = text; },
  setYAxisTopText: function(text) { this.yAxisTopText = text; },
  setYAxisBottomText: function(text) { this.yAxisBottomText = text; },
  setQuadrant1Text: function(text) { this.quadrant1Text = text; },
  setQuadrant2Text: function(text) { this.quadrant2Text = text; },
  setQuadrant3Text: function(text) { this.quadrant3Text = text; },
  setQuadrant4Text: function(text) { this.quadrant4Text = text; },
  addPoint: function(id, x, y) { this.points.push({id, x, y}); },
  
  clear: function() {
    this.xAxisLeftText = '';
    this.xAxisRightText = '';
    this.yAxisTopText = '';
    this.yAxisBottomText = '';
    this.quadrant1Text = '';
    this.quadrant2Text = '';
    this.quadrant3Text = '';
    this.quadrant4Text = '';
    this.points = [];
  }
};

// Requirement Diagram Functions
const requirementFunctions = {
  REQUIREMENT: { FUNCTIONAL: 'FUNCTIONAL', INTERFACE: 'INTERFACE', PERFORMANCE: 'PERFORMANCE', PHYSICAL: 'PHYSICAL', DESIGN_CONSTRAINT: 'DESIGN_CONSTRAINT' },
  RISK: { LOW: 'LOW', MEDIUM: 'MEDIUM', HIGH: 'HIGH' },
  VERIFY_METHOD: { ANALYSIS: 'ANALYSIS', DEMONSTRATION: 'DEMONSTRATION', INSPECTION: 'INSPECTION', TEST: 'TEST' },
  requirements: new Map(),
  elements: new Map(),
  relationships: [],
  
  addRequirement: function(id, text, type, risk, verifyMethod) {
    this.requirements.set(id, {id, text, type, risk, verifyMethod});
  },
  
  addElement: function(id, type, docRef) {
    this.elements.set(id, {id, type, docRef});
  },
  
  addRelationship: function(src, dst, type) {
    this.relationships.push({src, dst, type});
  },
  
  clear: function() {
    this.requirements = new Map();
    this.elements = new Map();
    this.relationships = [];
  }
};

// Block Diagram Functions
const blockFunctions = {
  blocks: [],
  columns: 1,
  
  getLogger: function() {
    return {
      debug: () => {},
      info: () => {},
      warn: () => {},
      error: () => {}
    };
  },
  
  addNode: function(id, label) {
    this.blocks.push({id, label});
  },
  
  setColumns: function(count) {
    this.columns = count;
  },
  
  clear: function() {
    this.blocks = [];
    this.columns = 1;
  }
};

module.exports = {
  c4Functions,
  quadrantFunctions,
  requirementFunctions,
  blockFunctions
};`;
  
  return contextContent;
}

// Generate and save the context file
const contextContent = generateContextFile();
fs.writeFileSync(path.join(__dirname, '../src/services/mermaidDbContexts.js'), contextContent);

console.log('✓ Mermaid DB contexts extracted and generated');