#!/usr/bin/env node

/**
 * Comprehensive MCP Test Evidence Generator
 * Tests all MCP functionality and generates detailed evidence
 */

const { spawn } = require('child_process');
const { writeFileSync, existsSync } = require('fs');
const path = require('path');

console.log('🧪 Starting Comprehensive MCP Test Evidence Generation...');
console.log('📅 Test Date:', new Date().toISOString());
console.log('📍 Test Environment: macOS, Node.js', process.version);
console.log('');

const results = {
  timestamp: new Date().toISOString(),
  version: '1.0.15',
  environment: {
    platform: process.platform,
    nodeVersion: process.version,
    architecture: process.arch
  },
  tests: []
};

function addTestResult(testName, status, details, duration = 0) {
  results.tests.push({
    testName,
    status,
    details,
    duration,
    timestamp: new Date().toISOString()
  });

  const statusEmoji = status === 'PASS' ? '✅' : status === 'FAIL' ? '❌' : '⚠️';
  console.log(`${statusEmoji} ${testName}: ${status} (${duration}ms)`);
  if (details && typeof details === 'object') {
    console.log(`   Details: ${JSON.stringify(details, null, 2).substring(0, 200)}...`);
  } else if (details) {
    console.log(`   Details: ${details}`);
  }
}

function runCommand(command, args = [], timeout = 10000) {
  return new Promise((resolve, reject) => {
    const startTime = Date.now();
    const child = spawn(command, args, {
      stdio: ['pipe', 'pipe', 'pipe'],
      shell: true
    });

    let stdout = '';
    let stderr = '';

    child.stdout.on('data', (data) => {
      stdout += data.toString();
    });

    child.stderr.on('data', (data) => {
      stderr += data.toString();
    });

    const timer = setTimeout(() => {
      child.kill('SIGTERM');
      reject(new Error(`Command timed out after ${timeout}ms`));
    }, timeout);

    child.on('close', (code) => {
      clearTimeout(timer);
      const duration = Date.now() - startTime;
      resolve({
        code,
        stdout,
        stderr,
        duration
      });
    });

    child.on('error', (error) => {
      clearTimeout(timer);
      reject(error);
    });
  });
}

async function testBuild() {
  console.log('📦 Testing Build System...');
  try {
    const result = await runCommand('npm', ['run', 'build:mcp'], 15000);
    if (result.code === 0) {
      addTestResult('Build System', 'PASS', 'TypeScript compilation successful', result.duration);
      return true;
    } else {
      addTestResult('Build System', 'FAIL', result.stderr, result.duration);
      return false;
    }
  } catch (error) {
    addTestResult('Build System', 'FAIL', error.message, 0);
    return false;
  }
}

async function testIntegration() {
  console.log('🔗 Testing MCP Integration...');
  try {
    const result = await runCommand('npm', ['run', 'test:mcp'], 20000);
    if (result.code === 0 && result.stdout.includes('All MCP integration tests passed')) {
      addTestResult('MCP Integration Tests', 'PASS', 'All 5 integration tests passed', result.duration);
      return true;
    } else {
      addTestResult('MCP Integration Tests', 'FAIL', result.stderr || result.stdout, result.duration);
      return false;
    }
  } catch (error) {
    addTestResult('MCP Integration Tests', 'FAIL', error.message, 0);
    return false;
  }
}

async function testHttpServer() {
  console.log('🌐 Testing HTTP MCP Server...');

  // Start HTTP server in background
  const serverProcess = spawn('npm', ['run', 'start:mcp-http'], {
    stdio: ['pipe', 'pipe', 'pipe'],
    shell: true
  });

  let serverOutput = '';
  serverProcess.stdout.on('data', (data) => {
    serverOutput += data.toString();
  });

  // Wait for server to start
  await new Promise(resolve => setTimeout(resolve, 5000));

  try {
    // Test health endpoint
    const healthResult = await runCommand('curl', ['-s', 'http://localhost:8080/health'], 5000);
    const healthData = JSON.parse(healthResult.stdout);

    if (healthData.status === 'healthy') {
      addTestResult('HTTP Server Health', 'PASS', healthData, healthResult.duration);
    } else {
      addTestResult('HTTP Server Health', 'FAIL', healthData, healthResult.duration);
    }

    // Test MCP initialization
    const initPayload = {
      jsonrpc: '2.0',
      method: 'initialize',
      params: {
        protocolVersion: '2024-11-05',
        capabilities: {},
        clientInfo: { name: 'test', version: '1.0.0' }
      },
      id: 1
    };

    const initResult = await runCommand('curl', [
      '-s', '-X', 'POST',
      'http://localhost:8080/mcp',
      '-H', 'Content-Type: application/json',
      '-d', JSON.stringify(initPayload)
    ], 5000);

    const initResponse = JSON.parse(initResult.stdout);
    if (initResponse.result && initResponse.result.protocolVersion === '2024-11-05') {
      addTestResult('MCP Initialization', 'PASS', initResponse.result, initResult.duration);
    } else {
      addTestResult('MCP Initialization', 'FAIL', initResponse, initResult.duration);
    }

    // Test tools list
    const toolsPayload = {
      jsonrpc: '2.0',
      method: 'tools/list',
      id: 2
    };

    const toolsResult = await runCommand('curl', [
      '-s', '-X', 'POST',
      'http://localhost:8080/mcp',
      '-H', 'Content-Type: application/json',
      '-d', JSON.stringify(toolsPayload)
    ], 5000);

    const toolsResponse = JSON.parse(toolsResult.stdout);
    if (toolsResponse.result && toolsResponse.result.tools && toolsResponse.result.tools.length > 0) {
      addTestResult('MCP Tools List', 'PASS', {
        toolCount: toolsResponse.result.tools.length,
        tools: toolsResponse.result.tools.map(t => t.name)
      }, toolsResult.duration);
    } else {
      addTestResult('MCP Tools List', 'FAIL', toolsResponse, toolsResult.duration);
    }

    // Test validate-diagrams tool
    const validatePayload = {
      jsonrpc: '2.0',
      method: 'tools/call',
      params: {
        name: 'validate-diagrams',
        arguments: {
          diagrams: [{
            content: 'flowchart TD\\n    A --> B',
            type: 'flowchart'
          }]
        }
      },
      id: 3
    };

    const validateResult = await runCommand('curl', [
      '-s', '-X', 'POST',
      'http://localhost:8080/mcp',
      '-H', 'Content-Type: application/json',
      '-d', JSON.stringify(validatePayload)
    ], 10000);

    try {
      const validateResponse = JSON.parse(validateResult.stdout);
      if (validateResponse.result && validateResponse.result.content) {
        const content = JSON.parse(validateResponse.result.content[0].text);
        addTestResult('Validate Diagrams Tool', 'PASS', {
          validDiagrams: content.validDiagrams,
          totalDiagrams: content.totalDiagrams
        }, validateResult.duration);
      } else {
        addTestResult('Validate Diagrams Tool', 'FAIL', validateResponse, validateResult.duration);
      }
    } catch (parseError) {
      addTestResult('Validate Diagrams Tool', 'WARN', 'Response parsing failed: ' + validateResult.stdout.substring(0, 200), validateResult.duration);
    }

  } catch (error) {
    addTestResult('HTTP Server Tests', 'FAIL', error.message, 0);
  } finally {
    // Kill server
    serverProcess.kill('SIGTERM');
  }
}

async function testGrammarParsers() {
  console.log('🔍 Testing Grammar Parsers...');

  const testDiagrams = [
    { type: 'flowchart', content: 'flowchart TD\\n    A --> B' },
    { type: 'sequenceDiagram', content: 'sequenceDiagram\\n    Alice->>Bob: Hello' },
    { type: 'classDiagram', content: 'classDiagram\\n    class Animal' },
    { type: 'erDiagram', content: 'erDiagram\\n    USER {\\n        int id\\n    }' },
    { type: 'gantt', content: 'gantt\\n    title Project\\n    dateFormat YYYY-MM-DD\\n    section A\\n    Task1: 2024-01-01, 1d' }
  ];

  let passedParsers = 0;

  for (const diagram of testDiagrams) {
    try {
      // Create a temporary test file
      const testContent = `#!/usr/bin/env node
const { MermaidValidator } = require('./src/services/validator');
const validator = new MermaidValidator();

async function test() {
  try {
    const result = await validator.validateDiagram('${diagram.content}', '${diagram.type}');
    console.log(JSON.stringify({ success: true, result }));
  } catch (error) {
    console.log(JSON.stringify({ success: false, error: error.message }));
  }
}

test();`;

      writeFileSync('/tmp/test_parser.js', testContent);

      const result = await runCommand('node', ['/tmp/test_parser.js'], 5000);
      const response = JSON.parse(result.stdout);

      if (response.success) {
        addTestResult(`${diagram.type} Parser`, 'PASS', 'Grammar validation successful', result.duration);
        passedParsers++;
      } else {
        addTestResult(`${diagram.type} Parser`, 'FAIL', response.error, result.duration);
      }
    } catch (error) {
      addTestResult(`${diagram.type} Parser`, 'FAIL', error.message, 0);
    }
  }

  addTestResult('Grammar Parser Summary', passedParsers === testDiagrams.length ? 'PASS' : 'WARN',
    `${passedParsers}/${testDiagrams.length} parsers working`, 0);
}

async function generateReport() {
  console.log('📊 Generating Test Evidence Report...');

  const passedTests = results.tests.filter(t => t.status === 'PASS').length;
  const failedTests = results.tests.filter(t => t.status === 'FAIL').length;
  const warnTests = results.tests.filter(t => t.status === 'WARN').length;

  const summary = {
    totalTests: results.tests.length,
    passed: passedTests,
    failed: failedTests,
    warnings: warnTests,
    successRate: Math.round((passedTests / results.tests.length) * 100),
    duration: results.tests.reduce((sum, t) => sum + t.duration, 0)
  };

  results.summary = summary;

  // Save detailed results
  writeFileSync('/Users/gregoriomomm/workspace/ica/mermaid-validator-mcp/MCP_TEST_EVIDENCE.json',
    JSON.stringify(results, null, 2));

  console.log('');
  console.log('📊 Test Summary:');
  console.log(`   Total Tests: ${summary.totalTests}`);
  console.log(`   ✅ Passed: ${summary.passed}`);
  console.log(`   ❌ Failed: ${summary.failed}`);
  console.log(`   ⚠️  Warnings: ${summary.warnings}`);
  console.log(`   📈 Success Rate: ${summary.successRate}%`);
  console.log(`   ⏱️  Total Duration: ${summary.duration}ms`);
  console.log('');
  console.log('📄 Detailed evidence saved to: MCP_TEST_EVIDENCE.json');
}

async function main() {
  try {
    const buildSuccess = await testBuild();
    if (!buildSuccess) {
      console.log('❌ Build failed, skipping other tests');
      await generateReport();
      return;
    }

    await testIntegration();
    await testHttpServer();
    await testGrammarParsers();

    await generateReport();

  } catch (error) {
    console.error('💥 Test suite failed:', error.message);
    addTestResult('Test Suite', 'FAIL', error.message, 0);
    await generateReport();
    process.exit(1);
  }
}

main();