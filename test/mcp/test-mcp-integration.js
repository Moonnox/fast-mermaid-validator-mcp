#!/usr/bin/env node

/**
 * MCP Integration Test Suite
 * Comprehensive tests for all MCP server transports and functionality
 */

const { spawn } = require('child_process');
const http = require('http');
const assert = require('assert');

class MCPTestSuite {
  constructor() {
    this.testResults = {
      passed: 0,
      failed: 0,
      errors: []
    };
  }

  async runAllTests() {
    console.log('🧪 Starting MCP Integration Test Suite...\n');

    try {
      await this.testStdioTransport();
      await this.testHttpTransport();
      await this.testSecureTransport();

      this.printResults();
    } catch (error) {
      console.error('❌ Test suite failed:', error);
      this.testResults.failed++;
      this.testResults.errors.push(error.message);
    }

    return this.testResults.failed === 0;
  }

  async testStdioTransport() {
    console.log('📋 Testing Stdio Transport...');

    const serverProcess = spawn('node', ['dist/mcp/server.js'], {
      stdio: ['pipe', 'pipe', 'pipe']
    });

    let serverOutput = '';
    serverProcess.stdout.on('data', (data) => {
      serverOutput += data.toString();
    });

    // Wait for server to initialize
    await new Promise(resolve => setTimeout(resolve, 2000));

    // Test initialization
    const initMessage = {
      jsonrpc: "2.0",
      id: 1,
      method: "initialize",
      params: {
        protocolVersion: "2024-11-05",
        capabilities: {},
        clientInfo: { name: "test-client", version: "1.0.0" }
      }
    };

    serverProcess.stdin.write(JSON.stringify(initMessage) + '\n');
    await new Promise(resolve => setTimeout(resolve, 500));

    serverProcess.kill('SIGTERM');

    if (serverOutput.includes('Real grammar parsers initialized')) {
      console.log('  ✅ Stdio transport initialized successfully');
      this.testResults.passed++;
    } else {
      console.log('  ❌ Stdio transport failed to initialize');
      this.testResults.failed++;
      this.testResults.errors.push('Stdio transport initialization failed');
    }
  }

  async testHttpTransport() {
    console.log('📋 Testing HTTP Transport...');

    const serverProcess = spawn('node', ['dist/mcp/server-http.js'], {
      env: { ...process.env, MCP_HTTP_PORT: '8090' },
      stdio: 'pipe'
    });

    // Wait for server to start
    await new Promise(resolve => setTimeout(resolve, 3000));

    try {
      // Test health endpoint
      const healthResponse = await this.makeHttpRequest('http://localhost:8090/health');
      assert(healthResponse.status === 'healthy');
      console.log('  ✅ Health endpoint working');
      this.testResults.passed++;

      // Test MCP initialization
      const mcpResponse = await this.makeHttpRequest('http://localhost:8090/mcp', 'POST', {
        jsonrpc: "2.0",
        method: "initialize",
        params: {
          protocolVersion: "2024-11-05",
          capabilities: {},
          clientInfo: { name: "test", version: "1.0.0" }
        },
        id: 1
      });
      assert(mcpResponse.result.serverInfo.name === 'mermaid-validator');
      console.log('  ✅ MCP protocol working');
      this.testResults.passed++;

    } catch (error) {
      console.log('  ❌ HTTP transport test failed:', error.message);
      this.testResults.failed++;
      this.testResults.errors.push(`HTTP transport: ${error.message}`);
    } finally {
      serverProcess.kill('SIGTERM');
    }
  }

  async testSecureTransport() {
    console.log('📋 Testing Secure Transport...');

    const serverProcess = spawn('node', ['dist/mcp/server-secure.js'], {
      env: { ...process.env, MCP_HTTP_PORT: '8091', NODE_ENV: 'development' },
      stdio: 'pipe'
    });

    // Wait for server to start
    await new Promise(resolve => setTimeout(resolve, 3000));

    try {
      // Test security features
      const healthResponse = await this.makeHttpRequest('http://localhost:8091/health');
      assert(healthResponse.status === 'healthy');
      console.log('  ✅ Secure transport health check');
      this.testResults.passed++;

      // Test invalid content type handling
      try {
        await this.makeHttpRequest('http://localhost:8091/mcp', 'POST', 'invalid', {
          'Content-Type': 'text/plain'
        });
        console.log('  ❌ Security validation failed - should reject invalid content type');
        this.testResults.failed++;
      } catch (error) {
        if (error.message.includes('400')) {
          console.log('  ✅ Content type validation working');
          this.testResults.passed++;
        } else {
          throw error;
        }
      }

    } catch (error) {
      console.log('  ❌ Secure transport test failed:', error.message);
      this.testResults.failed++;
      this.testResults.errors.push(`Secure transport: ${error.message}`);
    } finally {
      serverProcess.kill('SIGTERM');
    }
  }

  makeHttpRequest(url, method = 'GET', data = null, headers = {}) {
    return new Promise((resolve, reject) => {
      const urlObj = new URL(url);
      const options = {
        hostname: urlObj.hostname,
        port: urlObj.port,
        path: urlObj.pathname,
        method: method,
        headers: {
          'Content-Type': 'application/json',
          ...headers
        }
      };

      const req = http.request(options, (res) => {
        let responseData = '';
        res.on('data', chunk => responseData += chunk);
        res.on('end', () => {
          if (res.statusCode >= 400) {
            reject(new Error(`HTTP ${res.statusCode}: ${responseData}`));
          } else {
            try {
              resolve(JSON.parse(responseData));
            } catch {
              resolve(responseData);
            }
          }
        });
      });

      req.on('error', reject);

      if (data) {
        req.write(typeof data === 'string' ? data : JSON.stringify(data));
      }
      req.end();
    });
  }

  printResults() {
    console.log('\n📊 Test Results Summary:');
    console.log(`  ✅ Passed: ${this.testResults.passed}`);
    console.log(`  ❌ Failed: ${this.testResults.failed}`);

    if (this.testResults.errors.length > 0) {
      console.log('\n🔍 Errors:');
      this.testResults.errors.forEach(error => console.log(`  • ${error}`));
    }

    if (this.testResults.failed === 0) {
      console.log('\n🎉 All MCP integration tests passed!');
    } else {
      console.log('\n💥 Some tests failed. Please review the errors above.');
    }
  }
}

// Run tests if this file is executed directly
if (require.main === module) {
  const testSuite = new MCPTestSuite();
  testSuite.runAllTests().then(success => {
    process.exit(success ? 0 : 1);
  });
}

module.exports = MCPTestSuite;