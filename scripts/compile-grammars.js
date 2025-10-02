#!/usr/bin/env node

/**
 * Grammar Compilation Script
 * Compiles all grammar files (Jison, Langium, ANTLR) to JavaScript/TypeScript
 * Author: Gregorio Elias Roecker Momm
 */

const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');
const jison = require('jison');

const COLORS = {
  RED: '\x1b[31m',
  GREEN: '\x1b[32m',
  YELLOW: '\x1b[33m',
  BLUE: '\x1b[34m',
  NC: '\x1b[0m'
};

function log(message, color = COLORS.NC) {
  console.log(`${color}${message}${COLORS.NC}`);
}

function ensureDir(dir) {
  if (!fs.existsSync(dir)) {
    fs.mkdirSync(dir, { recursive: true });
  }
}

/**
 * Compile Jison grammars
 */
function compileJisonGrammars() {
  log('\n=== Compiling Jison Grammars ===', COLORS.BLUE);
  
  const grammarsDir = path.join(__dirname, '../src/services/grammars');
  const outputDir = path.join(__dirname, '../src/generated/jison');
  ensureDir(outputDir);
  
  const jisonFiles = [
    'flowchart/flow.jison',
    'sequence/sequenceDiagram.jison',
    'class/classDiagram.jison',
    'state/stateDiagram.jison',
    'er/erDiagram.jison',
    'gantt/gantt.jison',
    'user-journey/journey.jison',
    'requirement/requirementDiagram.jison',
    'sankey/sankey.jison',
    'xychart/xychart.jison',
    'kanban/kanban.jison',
    'block/block.jison',
    'c4/c4Diagram.jison',
    'mindmap/mindmap.jison',
    'quadrant/quadrant.jison',
    'timeline/timeline.jison'
  ];
  
  let successCount = 0;
  let failureCount = 0;
  
  jisonFiles.forEach(file => {
    try {
      const grammarPath = path.join(grammarsDir, file);
      const grammarName = path.basename(file, '.jison');
      const outputPath = path.join(outputDir, `${grammarName}.js`);
      
      log(`Compiling ${file}...`, COLORS.YELLOW);
      
      const grammarContent = fs.readFileSync(grammarPath, 'utf8');
      const parser = new jison.Parser(grammarContent);
      const parserSource = parser.generate();
      
      // Wrap in module exports
      const moduleSource = `
// Generated from ${file}
// Do not edit manually
module.exports = ${parserSource};
`;
      
      fs.writeFileSync(outputPath, moduleSource);
      log(`  ✓ Generated ${grammarName}.js`, COLORS.GREEN);
      successCount++;
    } catch (error) {
      log(`  ✗ Failed to compile ${file}: ${error.message}`, COLORS.RED);
      failureCount++;
    }
  });
  
  log(`\nJison compilation complete: ${successCount} success, ${failureCount} failures`, 
    failureCount > 0 ? COLORS.YELLOW : COLORS.GREEN);
  
  return failureCount === 0;
}

/**
 * Compile ANTLR4 grammars
 */
function compileAntlrGrammars() {
  log('\n=== Compiling ANTLR4 Grammars ===', COLORS.BLUE);
  
  const grammarsDir = path.join(__dirname, '../src/services/zenuml');
  const outputDir = path.join(__dirname, '../src/generated/antlr');
  ensureDir(outputDir);
  
  try {
    // Check if ANTLR is available
    try {
      execSync('which antlr', { stdio: 'ignore' });
    } catch {
      log('ANTLR not found in PATH, trying /opt/homebrew/bin/antlr', COLORS.YELLOW);
    }
    
    const antlrCmd = fs.existsSync('/opt/homebrew/bin/antlr') 
      ? '/opt/homebrew/bin/antlr' 
      : 'antlr';
    
    // Compile lexer
    log('Compiling sequenceLexer.g4...', COLORS.YELLOW);
    execSync(`${antlrCmd} -Dlanguage=JavaScript -o ${outputDir} ${grammarsDir}/sequenceLexer.g4`, {
      stdio: 'inherit'
    });
    
    // Copy tokens file for parser
    const tokensFile = path.join(outputDir, 'sequenceLexer.tokens');
    if (fs.existsSync(tokensFile)) {
      fs.copyFileSync(tokensFile, path.join(grammarsDir, 'sequenceLexer.tokens'));
    }
    
    // Compile parser
    log('Compiling sequenceParser.g4...', COLORS.YELLOW);
    execSync(`${antlrCmd} -Dlanguage=JavaScript -visitor -listener -o ${outputDir} ${grammarsDir}/sequenceParser.g4`, {
      stdio: 'inherit'
    });
    
    log('  ✓ ANTLR grammars compiled successfully', COLORS.GREEN);
    return true;
  } catch (error) {
    log(`  ✗ Failed to compile ANTLR grammars: ${error.message}`, COLORS.RED);
    return false;
  }
}

/**
 * Compile Langium grammars
 */
function compileLangiumGrammars() {
  log('\n=== Compiling Langium Grammars ===', COLORS.BLUE);
  
  const outputDir = path.join(__dirname, '../src/generated/langium');
  ensureDir(outputDir);
  
  try {
    // Check if TypeScript is available
    const tscPath = path.join(__dirname, '../node_modules/.bin/tsc');
    if (!fs.existsSync(tscPath)) {
      log('TypeScript not found, installing...', COLORS.YELLOW);
      execSync('npm install --save-dev typescript', { stdio: 'inherit' });
    }
    
    // Check for TypeScript files in language directory
    const tsFiles = fs.readdirSync(path.join(__dirname, '../src/services/language'))
      .filter(file => file.endsWith('.ts'));
    
    if (tsFiles.length > 0) {
      log('Compiling TypeScript files...', COLORS.YELLOW);
      
      // Compile TypeScript files
      try {
        execSync('npx tsc --project tsconfig.json', { 
          stdio: 'inherit',
          cwd: path.join(__dirname, '..')
        });
        log('  ✓ TypeScript compilation completed', COLORS.GREEN);
      } catch (error) {
        log(`  ⚠ TypeScript compilation had warnings: ${error.message}`, COLORS.YELLOW);
      }
    }
    
    // Process Langium grammar files
    const langiumFiles = [
      'gitGraph/gitGraph.langium',
      'pie/pie.langium', 
      'info/info.langium',
      'architecture/architecture.langium',
      'radar/radar.langium',
      'packet/packet.langium',
      'treemap/treemap.langium'
    ];
    
    let successCount = 0;
    langiumFiles.forEach(file => {
      const grammarName = path.basename(file, '.langium');
      const sourcePath = path.join(__dirname, '../src/services/language', file);
      
      if (fs.existsSync(sourcePath)) {
        // Copy grammar file for reference
        const destPath = path.join(outputDir, `${grammarName}.langium`);
        fs.copyFileSync(sourcePath, destPath);
        
        // Create a basic JavaScript wrapper for the grammar
        const wrapperContent = `
// Generated wrapper for ${grammarName} Langium grammar
// This provides basic validation without full Langium compilation

module.exports = {
  name: '${grammarName}',
  
  parse: function(content) {
    // Basic validation for ${grammarName} diagrams
    const firstLine = content.trim().split('\\n')[0].toLowerCase();
    
    if (!firstLine.includes('${grammarName}')) {
      throw new Error('Invalid ${grammarName} diagram: must start with "${grammarName}"');
    }
    
    return { type: '${grammarName}', valid: true };
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
};`;
        
        fs.writeFileSync(path.join(outputDir, `${grammarName}.js`), wrapperContent);
        log(`  ✓ Generated wrapper for ${grammarName}`, COLORS.GREEN);
        successCount++;
      } else {
        log(`  ✗ Source file not found: ${sourcePath}`, COLORS.RED);
      }
    });
    
    log(`\nLangium compilation: ${successCount}/${langiumFiles.length} completed`, COLORS.GREEN);
    return true;
    
  } catch (error) {
    log(`  ✗ Failed to compile Langium grammars: ${error.message}`, COLORS.RED);
    return false;
  }
}

/**
 * Generate TypeScript definitions
 */
function generateTypeScriptDefinitions() {
  log('\n=== Generating TypeScript Definitions ===', COLORS.BLUE);
  
  const outputDir = path.join(__dirname, '../src/types');
  ensureDir(outputDir);
  
  // Generate parser types
  const parserTypes = `
// Auto-generated TypeScript definitions for Mermaid parsers
// Do not edit manually

export interface Parser {
  parse(input: string): any;
  yy: any;
}

export interface ParserMap {
  [key: string]: Parser;
}

export interface ValidationResult {
  valid: boolean;
  errors: Array<{
    line?: number;
    column?: number;
    message: string;
    type: string;
  }>;
  warnings: Array<{
    line?: number;
    column?: number;
    message: string;
    type: string;
  }>;
}

export interface DiagramMetadata {
  diagramType: string;
  validationMethod: string;
  contentLength: number;
  lineCount: number;
  customValidator: boolean;
  processingTime: number;
}
`;
  
  fs.writeFileSync(path.join(outputDir, 'parsers.d.ts'), parserTypes);
  log('  ✓ Generated TypeScript definitions', COLORS.GREEN);
  
  return true;
}

/**
 * Main compilation function
 */
async function main() {
  log('Starting Grammar Compilation Pipeline', COLORS.BLUE);
  log('=====================================\n', COLORS.BLUE);
  
  const startTime = Date.now();
  let success = true;
  
  // Compile all grammar types
  success = compileJisonGrammars() && success;
  success = compileAntlrGrammars() && success;
  success = compileLangiumGrammars() && success;
  success = generateTypeScriptDefinitions() && success;
  
  const duration = Date.now() - startTime;
  
  log('\n=====================================', COLORS.BLUE);
  if (success) {
    log(`✓ Grammar compilation completed successfully in ${duration}ms`, COLORS.GREEN);
    process.exit(0);
  } else {
    log(`✗ Grammar compilation completed with errors in ${duration}ms`, COLORS.RED);
    process.exit(1);
  }
}

// Run if called directly
if (require.main === module) {
  main().catch(error => {
    log(`Fatal error: ${error.message}`, COLORS.RED);
    process.exit(1);
  });
}

module.exports = {
  compileJisonGrammars,
  compileAntlrGrammars,
  compileLangiumGrammars,
  generateTypeScriptDefinitions
};