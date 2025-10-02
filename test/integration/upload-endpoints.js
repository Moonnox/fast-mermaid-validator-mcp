#!/usr/bin/env node

/**
 * Upload Endpoints Test Suite
 * Tests file upload functionality with various file types
 * Author: Gregorio Elias Roecker Momm
 */

const http = require('http');
const fs = require('fs');
const path = require('path');
const FormData = require('form-data');

const API_HOST = process.env.API_HOST || 'localhost';
const API_PORT = process.env.API_PORT || 8000;
const API_BASE = `http://${API_HOST}:${API_PORT}/api/v1`;

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

/**
 * Create test files
 */
function createTestFiles() {
  const testDir = path.join(__dirname, 'test-files');
  if (!fs.existsSync(testDir)) {
    fs.mkdirSync(testDir, { recursive: true });
  }
  
  // Create markdown file with multiple diagrams
  const markdownContent = `# Test Document

## Flowchart Example

\`\`\`mermaid
flowchart TD
    A[Start] --> B{Decision}
    B -->|Yes| C[Process]
    B -->|No| D[End]
\`\`\`

## Sequence Diagram

\`\`\`mermaid
sequenceDiagram
    Alice->>Bob: Hello Bob
    Bob-->>Alice: Hi Alice
\`\`\`

## Class Diagram

\`\`\`mermaid
classDiagram
    class Animal {
        +String name
        +int age
        +makeSound()
    }
\`\`\`
`;
  
  fs.writeFileSync(path.join(testDir, 'test.md'), markdownContent);
  
  // Create single diagram file
  const singleDiagramContent = `\`\`\`mermaid
graph TD
    A --> B
    B --> C
    C --> D
\`\`\``;
  
  fs.writeFileSync(path.join(testDir, 'single.md'), singleDiagramContent);
  
  // Create plain text file with mermaid content
  const plainTextContent = `flowchart LR
    Start --> Stop`;
  
  fs.writeFileSync(path.join(testDir, 'diagram.txt'), plainTextContent);
  
  // Create invalid markdown
  const invalidContent = `# Invalid Document

\`\`\`mermaid
flowchart TD
    A --> // Invalid syntax
\`\`\``;
  
  fs.writeFileSync(path.join(testDir, 'invalid.md'), invalidContent);
  
  // Create empty file
  fs.writeFileSync(path.join(testDir, 'empty.md'), '');
  
  // Create large file
  let largeContent = '# Large Document\n\n';
  for (let i = 0; i < 100; i++) {
    largeContent += `
## Section ${i}

\`\`\`mermaid
flowchart TD
    Node${i}A --> Node${i}B
    Node${i}B --> Node${i}C
\`\`\`
`;
  }
  fs.writeFileSync(path.join(testDir, 'large.md'), largeContent);
  
  return testDir;
}

/**
 * Upload file test
 */
async function testFileUpload(filePath, options = {}) {
  return new Promise((resolve, reject) => {
    const form = new FormData();
    
    // Add file
    form.append('file', fs.createReadStream(filePath));
    
    // Add options
    if (options.theme) {
      form.append('theme', options.theme);
    }
    
    const requestOptions = {
      hostname: API_HOST,
      port: API_PORT,
      path: '/api/v1/upload/file',
      method: 'POST',
      headers: form.getHeaders()
    };
    
    const req = http.request(requestOptions, (res) => {
      let body = '';
      res.on('data', chunk => body += chunk);
      res.on('end', () => {
        try {
          resolve({
            statusCode: res.statusCode,
            body: body ? JSON.parse(body) : null
          });
        } catch (error) {
          reject(new Error(`Failed to parse response: ${error.message}`));
        }
      });
    });
    
    req.on('error', reject);
    form.pipe(req);
  });
}

// Text upload endpoint removed - use file upload with text files instead

/**
 * Test file upload scenarios
 */
async function runFileUploadTests(testDir) {
  log('\n=== Testing File Upload ===', COLORS.BLUE);
  
  const tests = [
    {
      name: 'Valid markdown with multiple diagrams',
      file: 'test.md',
      expectedStatus: 200,
      options: {}
    },
    {
      name: 'Single diagram markdown',
      file: 'single.md',
      expectedStatus: 200,
      options: {}
    },
    {
      name: 'Plain text diagram',
      file: 'diagram.txt',
      expectedStatus: 200,
      options: {}
    },
    {
      name: 'Invalid diagram syntax',
      file: 'invalid.md',
      expectedStatus: 200, // Should still process but mark as invalid
      options: {}
    },
    {
      name: 'Empty file',
      file: 'empty.md',
      expectedStatus: 200,
      options: {}
    },
    {
      name: 'Large file with 100 diagrams',
      file: 'large.md',
      expectedStatus: 200,
      options: {}
    }
  ];
  
  let passCount = 0;
  let failCount = 0;
  
  for (const test of tests) {
    process.stdout.write(`  Testing ${test.name}... `);
    
    try {
      const filePath = path.join(testDir, test.file);
      const response = await testFileUpload(filePath, test.options);
      
      if (response.statusCode === test.expectedStatus) {
        log('✓', COLORS.GREEN);
        passCount++;
        
        // Log additional info for successful tests
        if (response.body?.totalDiagrams) {
          log(`    Found ${response.body.totalDiagrams} diagrams, ${response.body.validDiagrams} valid`, COLORS.BLUE);
        }
      } else {
        log(`✗ (Expected ${test.expectedStatus}, got ${response.statusCode})`, COLORS.RED);
        failCount++;
      }
    } catch (error) {
      log(`✗ (${error.message})`, COLORS.RED);
      failCount++;
    }
  }
  
  return { passCount, failCount };
}

// Text upload tests removed - use file upload instead

/**
 * Main test runner
 */
async function main() {
  log('Upload Endpoints Test Suite', COLORS.BLUE);
  log('===========================\n', COLORS.BLUE);
  
  // Create test files
  log('Creating test files...', COLORS.YELLOW);
  const testDir = createTestFiles();
  log('Test files created', COLORS.GREEN);
  
  // Run tests
  const fileResults = await runFileUploadTests(testDir);
  
  // Summary
  log('\n=== Test Summary ===', COLORS.BLUE);
  const totalPass = fileResults.passCount;
  const totalFail = fileResults.failCount;
  const totalTests = totalPass + totalFail;
  
  log(`Total tests: ${totalTests}`, COLORS.BLUE);
  log(`Passed: ${totalPass}`, COLORS.GREEN);
  log(`Failed: ${totalFail}`, totalFail > 0 ? COLORS.RED : COLORS.GREEN);
  
  // Cleanup
  log('\nCleaning up test files...', COLORS.YELLOW);
  fs.rmSync(testDir, { recursive: true, force: true });
  log('Cleanup complete', COLORS.GREEN);
  
  // Exit code
  process.exit(totalFail > 0 ? 1 : 0);
}

// Run tests
if (require.main === module) {
  main().catch(error => {
    log(`Fatal error: ${error.message}`, COLORS.RED);
    process.exit(1);
  });
}

module.exports = { testFileUpload };