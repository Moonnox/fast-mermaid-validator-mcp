#!/usr/bin/env node
/**
 * Secret Key Authentication Test Script
 * 
 * This script demonstrates how to use the secret key authentication feature
 * with the Mermaid Validator API.
 * 
 * Usage:
 *   node examples/test-secret-key.js
 * 
 * Environment Variables:
 *   API_URL - Base URL of the API (default: http://localhost:8000)
 *   SECRET_KEY - Secret key for authentication (default: your-secret-key-here)
 */

const http = require('http');
const https = require('https');

// Configuration
const API_URL = process.env.API_URL || 'http://localhost:8000';
const SECRET_KEY = process.env.SECRET_KEY || 'your-secret-key-here';
const SECRET_KEY_HEADER = process.env.SECRET_KEY_HEADER || 'x-secret-key';

// Test data
const testDiagram = {
  diagrams: [
    {
      content: 'flowchart TD\n  A[Start] --> B[Process]\n  B --> C[End]',
      type: 'flowchart',
      id: 'test-diagram-1'
    }
  ]
};

// Colors for console output
const colors = {
  reset: '\x1b[0m',
  green: '\x1b[32m',
  red: '\x1b[31m',
  yellow: '\x1b[33m',
  blue: '\x1b[34m',
  cyan: '\x1b[36m'
};

/**
 * Make HTTP request
 */
function makeRequest(url, options, data) {
  return new Promise((resolve, reject) => {
    const urlObj = new URL(url);
    const protocol = urlObj.protocol === 'https:' ? https : http;

    const reqOptions = {
      hostname: urlObj.hostname,
      port: urlObj.port || (urlObj.protocol === 'https:' ? 443 : 80),
      path: urlObj.pathname,
      method: options.method || 'GET',
      headers: options.headers || {}
    };

    const req = protocol.request(reqOptions, (res) => {
      let responseData = '';

      res.on('data', (chunk) => {
        responseData += chunk;
      });

      res.on('end', () => {
        try {
          const parsed = JSON.parse(responseData);
          resolve({
            statusCode: res.statusCode,
            headers: res.headers,
            body: parsed
          });
        } catch (error) {
          resolve({
            statusCode: res.statusCode,
            headers: res.headers,
            body: responseData
          });
        }
      });
    });

    req.on('error', (error) => {
      reject(error);
    });

    if (data) {
      req.write(JSON.stringify(data));
    }

    req.end();
  });
}

/**
 * Print test result
 */
function printResult(testName, passed, message, details) {
  const icon = passed ? '✓' : '✗';
  const color = passed ? colors.green : colors.red;
  
  console.log(`${color}${icon} ${testName}${colors.reset}`);
  console.log(`  ${message}`);
  
  if (details) {
    console.log(`  ${colors.cyan}Details:${colors.reset} ${JSON.stringify(details, null, 2).split('\n').join('\n  ')}`);
  }
  
  console.log('');
}

/**
 * Run tests
 */
async function runTests() {
  console.log(`${colors.blue}======================================${colors.reset}`);
  console.log(`${colors.blue}Secret Key Authentication Test Suite${colors.reset}`);
  console.log(`${colors.blue}======================================${colors.reset}`);
  console.log('');
  console.log(`API URL: ${API_URL}`);
  console.log(`Secret Key Header: ${SECRET_KEY_HEADER}`);
  console.log('');

  let totalTests = 0;
  let passedTests = 0;

  // Test 1: Health check without secret key (should pass)
  try {
    totalTests++;
    console.log(`${colors.yellow}Test 1: Health Check (no secret key required)${colors.reset}`);
    
    const response = await makeRequest(`${API_URL}/api/v1/health`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });

    const passed = response.statusCode === 200;
    if (passed) passedTests++;
    
    printResult(
      'Health Check',
      passed,
      `Expected 200, got ${response.statusCode}`,
      passed ? response.body : null
    );
  } catch (error) {
    printResult('Health Check', false, error.message);
  }

  // Test 2: Request without secret key (should fail with 401)
  try {
    totalTests++;
    console.log(`${colors.yellow}Test 2: Request without secret key (should fail)${colors.reset}`);
    
    const response = await makeRequest(`${API_URL}/api/v1/validate`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    }, testDiagram);

    const passed = response.statusCode === 401;
    if (passed) passedTests++;
    
    printResult(
      'Missing Secret Key',
      passed,
      `Expected 401, got ${response.statusCode}`,
      response.body
    );
  } catch (error) {
    printResult('Missing Secret Key', false, error.message);
  }

  // Test 3: Request with invalid secret key (should fail with 403)
  try {
    totalTests++;
    console.log(`${colors.yellow}Test 3: Request with invalid secret key (should fail)${colors.reset}`);
    
    const response = await makeRequest(`${API_URL}/api/v1/validate`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        [SECRET_KEY_HEADER]: 'invalid-secret-key-12345'
      }
    }, testDiagram);

    const passed = response.statusCode === 403;
    if (passed) passedTests++;
    
    printResult(
      'Invalid Secret Key',
      passed,
      `Expected 403, got ${response.statusCode}`,
      response.body
    );
  } catch (error) {
    printResult('Invalid Secret Key', false, error.message);
  }

  // Test 4: Request with valid secret key (should succeed)
  try {
    totalTests++;
    console.log(`${colors.yellow}Test 4: Request with valid secret key (should succeed)${colors.reset}`);
    
    const response = await makeRequest(`${API_URL}/api/v1/validate`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        [SECRET_KEY_HEADER]: SECRET_KEY
      }
    }, testDiagram);

    const passed = response.statusCode === 200;
    if (passed) passedTests++;
    
    printResult(
      'Valid Secret Key',
      passed,
      `Expected 200, got ${response.statusCode}`,
      passed ? { validationSuccess: response.body.results?.[0]?.isValid } : response.body
    );
  } catch (error) {
    printResult('Valid Secret Key', false, error.message);
  }

  // Test 5: MCP endpoint with valid secret key (if MCP server is running)
  try {
    totalTests++;
    console.log(`${colors.yellow}Test 5: MCP endpoint with valid secret key${colors.reset}`);
    
    const mcpUrl = API_URL.replace(':8000', ':8080');
    const response = await makeRequest(`${mcpUrl}/mcp`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        [SECRET_KEY_HEADER]: SECRET_KEY
      }
    }, {
      jsonrpc: '2.0',
      method: 'tools/list',
      id: 1
    });

    const passed = response.statusCode === 200 && response.body.result;
    if (passed) passedTests++;
    
    printResult(
      'MCP with Valid Secret Key',
      passed,
      passed ? `MCP server responded successfully` : `Expected 200, got ${response.statusCode}`,
      passed ? { toolCount: response.body.result?.tools?.length } : response.body
    );
  } catch (error) {
    printResult('MCP with Valid Secret Key', false, `MCP server not running or error: ${error.message}`);
  }

  // Summary
  console.log(`${colors.blue}======================================${colors.reset}`);
  console.log(`${colors.blue}Test Summary${colors.reset}`);
  console.log(`${colors.blue}======================================${colors.reset}`);
  console.log('');
  
  const allPassed = passedTests === totalTests;
  const summaryColor = allPassed ? colors.green : colors.red;
  
  console.log(`${summaryColor}Total Tests: ${totalTests}${colors.reset}`);
  console.log(`${colors.green}Passed: ${passedTests}${colors.reset}`);
  console.log(`${colors.red}Failed: ${totalTests - passedTests}${colors.reset}`);
  console.log('');
  
  if (allPassed) {
    console.log(`${colors.green}✓ All tests passed!${colors.reset}`);
  } else {
    console.log(`${colors.red}✗ Some tests failed${colors.reset}`);
  }
  
  console.log('');

  // Exit with appropriate code
  process.exit(allPassed ? 0 : 1);
}

// Run tests
console.log('');
runTests().catch((error) => {
  console.error(`${colors.red}Fatal error:${colors.reset}`, error);
  process.exit(1);
});

