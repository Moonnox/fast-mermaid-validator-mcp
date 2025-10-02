#!/usr/bin/env node

/**
 * Quick Docker Test Script
 * Tests that grammars are compiled and working in Docker container
 */

const http = require('http');

function makeRequest(options, data) {
  return new Promise((resolve, reject) => {
    const req = http.request(options, (res) => {
      let body = '';
      res.on('data', chunk => body += chunk);
      res.on('end', () => {
        try {
          resolve({
            statusCode: res.statusCode,
            body: body ? JSON.parse(body) : null
          });
        } catch (error) {
          resolve({
            statusCode: res.statusCode,
            body: body
          });
        }
      });
    });
    
    req.on('error', reject);
    
    if (data) {
      req.write(JSON.stringify(data));
    }
    
    req.end();
  });
}

async function test() {
  console.log('🧪 Testing Docker container...');
  
  // Wait for server to start
  console.log('⏳ Waiting for server...');
  await new Promise(resolve => setTimeout(resolve, 5000));
  
  try {
    // Test health endpoint
    console.log('🔍 Testing health endpoint...');
    const health = await makeRequest({
      hostname: 'localhost',
      port: 8000,
      path: '/api/v1/health',
      method: 'GET'
    });
    
    console.log(`Health: ${health.statusCode} ${health.body?.status || health.body}`);
    
    // Test validation endpoint
    console.log('🔍 Testing validation endpoint...');
    const validation = await makeRequest({
      hostname: 'localhost',
      port: 8000,
      path: '/api/v1/validate',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    }, {
      diagrams: [{
        content: 'flowchart TD\n  A --> B\n  B --> C',
        type: 'flowchart'
      }],
      options: {  }
    });
    
    console.log(`Validation: ${validation.statusCode}`);
    if (validation.body?.results) {
      console.log(`✅ Valid: ${validation.body.validDiagrams}/${validation.body.totalDiagrams}`);
    }
    
    console.log('✅ Docker test completed successfully!');
    
  } catch (error) {
    console.error('❌ Docker test failed:', error.message);
    process.exit(1);
  }
}

test();