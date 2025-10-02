#!/usr/bin/env node

const fs = require('fs');
const path = require('path');

// Simple markdown diagram extractor (similar to FileProcessor)
function extractDiagrams(content, filename) {
  const diagrams = [];
  const mermaidRegex = /```mermaid\s*\n([\s\S]*?)\n```/g;
  let match;
  let index = 0;

  while ((match = mermaidRegex.exec(content)) !== null) {
    const diagramContent = match[1].trim();
    if (diagramContent) {
      // Detect diagram type from first line
      const firstLine = diagramContent.split('\n')[0].trim().toLowerCase();
      let type = 'unknown';
      
      // Basic type detection
      if (firstLine.startsWith('flowchart') || firstLine.startsWith('graph')) type = firstLine.split(' ')[0];
      else if (firstLine.startsWith('sequencediagram')) type = 'sequenceDiagram';
      else if (firstLine.startsWith('classdiagram')) type = 'classDiagram';
      else if (firstLine.startsWith('statediagram')) type = firstLine.includes('-v2') ? 'stateDiagram-v2' : 'stateDiagram';
      else if (firstLine.startsWith('erdiagram')) type = 'erDiagram';
      else if (firstLine.startsWith('gantt')) type = 'gantt';
      else if (firstLine.startsWith('journey')) type = 'journey';
      else if (firstLine.startsWith('requirementdiagram')) type = 'requirement';
      else if (firstLine.startsWith('sankey-beta')) type = 'sankey-beta';
      else if (firstLine.startsWith('xychart-beta')) type = 'xychart-beta';
      else if (firstLine.startsWith('kanban')) type = 'kanban';
      else if (firstLine.startsWith('block-beta')) type = 'block';
      else if (firstLine.startsWith('c4')) type = 'c4';
      else if (firstLine.startsWith('mindmap')) type = 'mindmap';
      else if (firstLine.startsWith('quadrantchart')) type = 'quadrant';
      else if (firstLine.startsWith('timeline')) type = 'timeline';
      else if (firstLine.startsWith('pie')) type = 'pie';
      else if (firstLine.startsWith('gitgraph')) type = 'gitGraph';
      else if (firstLine.startsWith('info')) type = 'info';
      else if (firstLine.startsWith('architecture-beta')) type = 'architecture';
      else if (firstLine.startsWith('radar')) type = 'radar';
      else if (firstLine.startsWith('packet-beta')) type = 'packet';
      else if (firstLine.startsWith('treemap-beta')) type = 'treemap';
      else if (firstLine.startsWith('zenuml')) type = 'zenuml';
      
      diagrams.push({
        id: `${filename}_diagram_${++index}`,
        content: diagramContent,
        type: type,
        length: diagramContent.length,
        lines: diagramContent.split('\n').length
      });
    }
  }

  return diagrams;
}

// Process test files
const testFiles = [
  'test_all_26_diagrams_valid.md',
  'test_invalid_diagrams.md'
];

console.log('=== FILE UPLOAD PROCESSING EVIDENCE ===\n');

testFiles.forEach(filename => {
  if (fs.existsSync(filename)) {
    console.log(`📁 PROCESSING FILE: ${filename}`);
    console.log(`📊 FILE SIZE: ${fs.statSync(filename).size} bytes`);
    
    const content = fs.readFileSync(filename, 'utf8');
    const diagrams = extractDiagrams(content, filename);
    
    console.log(`🔍 EXTRACTED DIAGRAMS: ${diagrams.length}`);
    console.log('─'.repeat(50));
    
    diagrams.forEach((diagram, idx) => {
      console.log(`  ${idx + 1}. TYPE: ${diagram.type.padEnd(15)} | LENGTH: ${diagram.length.toString().padStart(4)} | LINES: ${diagram.lines}`);
      
      // Show first few lines of content as evidence
      const preview = diagram.content.split('\n').slice(0, 2).join(' ').substring(0, 50);
      console.log(`     PREVIEW: ${preview}${diagram.content.length > 50 ? '...' : ''}`);
    });
    
    console.log('\n');
  }
});

console.log('=== DIAGRAM TYPE SUMMARY ===');
const allFiles = testFiles.map(f => {
  if (fs.existsSync(f)) {
    const content = fs.readFileSync(f, 'utf8');
    return extractDiagrams(content, f);
  }
  return [];
}).flat();

const typeCounts = {};
allFiles.forEach(d => {
  typeCounts[d.type] = (typeCounts[d.type] || 0) + 1;
});

Object.entries(typeCounts).sort().forEach(([type, count]) => {
  console.log(`${type.padEnd(20)}: ${count} diagram(s)`);
});

console.log(`\n📈 TOTAL UNIQUE TYPES: ${Object.keys(typeCounts).length}`);
console.log(`📊 TOTAL DIAGRAMS: ${allFiles.length}`);