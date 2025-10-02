/**
 * Regression Test Suite for All Diagram Types
 * Tests validation of all 28 supported Mermaid diagram types
 */

const axios = require('axios');
const fs = require('fs');
const path = require('path');

const API_URL = process.env.API_URL || 'http://localhost:8000';
const TIMEOUT = 30000;

// Test data for all diagram types
const testDiagrams = {
  flowchart: {
    valid: "flowchart TD\n    A[Start] --> B[Process]\n    B --> C[End]",
    invalid: "flowchart TD\n    A[Start -->> B[Invalid]"
  },
  sequenceDiagram: {
    valid: "sequenceDiagram\n    Alice->>Bob: Hello\n    Bob-->>Alice: Hi there",
    invalid: "sequenceDiagram\n    Alice->>Bob:"
  },
  classDiagram: {
    valid: "classDiagram\n    class Animal\n    Animal : +int age\n    Animal : +String gender\n    Animal: +isMammal()",
    invalid: "classDiagram\n    class Animal\n    Animal ::"
  },
  stateDiagram: {
    valid: "stateDiagram-v2\n    [*] --> Still\n    Still --> [*]\n    Still --> Moving\n    Moving --> Still",
    invalid: "stateDiagram-v2\n    [*] -->"
  },
  erDiagram: {
    valid: "erDiagram\n    CUSTOMER ||--o{ ORDER : places\n    ORDER ||--|{ LINE-ITEM : contains",
    invalid: "erDiagram\n    CUSTOMER ||--o{"
  },
  gantt: {
    valid: "gantt\n    title A Gantt Diagram\n    dateFormat  YYYY-MM-DD\n    section Section\n    A task :a1, 2014-01-01, 30d",
    invalid: "gantt\n    title A Gantt Diagram\n    section Section\n    A task"
  },
  journey: {
    valid: "journey\n    title My working day\n    section Go to work\n      Make tea: 5: Me\n      Go upstairs: 3: Me",
    invalid: "journey\n    title My working day\n    section Go to work\n      Make tea"
  },
  'C4Context': {
    valid: "C4Context\n    title System Context diagram for Internet Banking System\n    Person(customerA, \"Banking Customer A\", \"A customer of the bank\")",
    invalid: "C4Context\n    title System Context\n    Person()"
  },
  mindmap: {
    valid: "mindmap\n  root((mindmap))\n    Origins\n      Long history\n    Research\n      On effectiveness<br/>and features",
    invalid: "mindmap\n  root(())\n    Origins"
  },
  timeline: {
    valid: "timeline\n    title History of Social Media Platform\n    2002 : LinkedIn\n    2004 : Facebook\n    2005 : Youtube",
    invalid: "timeline\n    title History\n    2002"
  },
  quadrantChart: {
    valid: "quadrantChart\n    title Reach and engagement of campaigns\n    x-axis Low Reach --> High Reach\n    y-axis Low Engagement --> High Engagement\n    quadrant-1 We should expand\n    quadrant-2 Need to promote\n    quadrant-3 Re-evaluate\n    quadrant-4 May be improved\n    Campaign A: [0.3, 0.6]\n    Campaign B: [0.45, 0.23]",
    invalid: "quadrantChart\n    title Reach\n    x-axis Low --> High"
  },
  requirementDiagram: {
    valid: "requirementDiagram\n    requirement test_req {\n    id: 1\n    text: the test text.\n    risk: high\n    verifymethod: test\n    }",
    invalid: "requirementDiagram\n    requirement test_req {\n    id:\n    }"
  },
  'gitGraph': {
    valid: "gitGraph\n    commit\n    commit\n    branch develop\n    checkout develop\n    commit",
    invalid: "gitGraph\n    commit\n    branch"
  },
  pie: {
    valid: "pie title Pets adopted by volunteers\n    \"Dogs\" : 386\n    \"Cats\" : 85\n    \"Rats\" : 15",
    invalid: "pie title Pets\n    \"Dogs\""
  },
  block: {
    valid: "block-beta\ncolumns 1\n  db[(\"Database\")]\n  block:ID\n    A\n    B\n    C\n  end",
    invalid: "block-beta\ncolumns\n  db"
  },
  'sankey-beta': {
    valid: "sankey-beta\n\nAgricultural 'waste',Bio-conversion,124.729\nBio-conversion,Liquid,0.597",
    invalid: "sankey-beta\n\nAgricultural"
  },
  'xychart-beta': {
    valid: "xychart-beta\n    title \"Sales Revenue\"\n    x-axis [jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec]\n    y-axis \"Revenue (in $)\" 4000 --> 11000\n    bar [5000, 6000, 7500, 8200, 9500, 10500, 11000, 10200, 9200, 8500, 7000, 6000]",
    invalid: "xychart-beta\n    title \"Sales\"\n    x-axis"
  },
  kanban: {
    valid: "kanban\n  Todo\n    [Create Documentation]\n  Doing\n    [Update Website]\n  Done\n    [Design new icons]\n    [Create wireframes]",
    invalid: "kanban\n  Todo\n    []"
  },
  packet: {
    valid: "packet-beta\n0-15: \"Source Port\"\n16-31: \"Destination Port\"",
    invalid: "packet-beta\n0-15:"
  },
  architecture: {
    valid: "architecture-beta\n    service api\n    service db\n    db:L -- R:api",
    invalid: "architecture-beta\n    service\n    service"
  },
  treemap: {
    valid: "treemap\n  title My Treemap\n  Parent\n    Child1: 100\n    Child2: 200",
    invalid: "treemap\n  title My Treemap\n  Parent\n    Child1"
  }
};

// Test results tracking
const results = {
  total: 0,
  passed: 0,
  failed: 0,
  errors: []
};

/**
 * Test a single diagram validation
 */
async function testDiagram(type, content, shouldBeValid) {
  results.total++;

  try {
    const response = await axios.post(`${API_URL}/api/v1/validate`, {
      diagrams: [{
        content: content,
        type: type
      }]
    }, { timeout: TIMEOUT });

    const result = response.data.results[0];
    const isValid = result.valid;

    if (isValid === shouldBeValid) {
      results.passed++;
      console.log(`✓ ${type} (${shouldBeValid ? 'valid' : 'invalid'}): PASS`);
      return true;
    } else {
      results.failed++;
      results.errors.push({
        type,
        expected: shouldBeValid,
        got: isValid,
        errors: result.errors || []
      });
      console.log(`✗ ${type} (${shouldBeValid ? 'valid' : 'invalid'}): FAIL - Expected ${shouldBeValid}, got ${isValid}`);
      return false;
    }
  } catch (error) {
    results.failed++;
    results.errors.push({
      type,
      expected: shouldBeValid,
      error: error.message
    });
    console.log(`✗ ${type} (${shouldBeValid ? 'valid' : 'invalid'}): ERROR - ${error.message}`);
    return false;
  }
}

/**
 * Run all diagram tests
 */
async function runAllTests() {
  console.log('='.repeat(80));
  console.log('MERMAID VALIDATOR REGRESSION TEST SUITE');
  console.log('='.repeat(80));
  console.log(`API URL: ${API_URL}`);
  console.log(`Total Diagram Types: ${Object.keys(testDiagrams).length}`);
  console.log('='.repeat(80));
  console.log('');

  // Test health endpoint first
  console.log('Testing health endpoint...');
  try {
    const health = await axios.get(`${API_URL}/api/v1/health`, { timeout: 5000 });
    console.log(`✓ Health check: ${health.data.status}`);
    console.log('');
  } catch (error) {
    console.log(`✗ Health check failed: ${error.message}`);
    console.log('Server may not be running. Start with: npm start');
    process.exit(1);
  }

  // Test all diagram types
  console.log('Running diagram validation tests...');
  console.log('');

  for (const [type, diagrams] of Object.entries(testDiagrams)) {
    // Test valid diagram
    if (diagrams.valid) {
      await testDiagram(type, diagrams.valid, true);
    }

    // Test invalid diagram
    if (diagrams.invalid) {
      await testDiagram(type, diagrams.invalid, false);
    }
  }

  // Print summary
  console.log('');
  console.log('='.repeat(80));
  console.log('TEST SUMMARY');
  console.log('='.repeat(80));
  console.log(`Total Tests: ${results.total}`);
  console.log(`Passed: ${results.passed} (${((results.passed/results.total)*100).toFixed(1)}%)`);
  console.log(`Failed: ${results.failed} (${((results.failed/results.total)*100).toFixed(1)}%)`);
  console.log('='.repeat(80));

  if (results.failed > 0) {
    console.log('');
    console.log('FAILED TESTS:');
    results.errors.forEach((error, index) => {
      console.log(`${index + 1}. ${error.type}: Expected ${error.expected}, got ${error.got || 'ERROR'}`);
      if (error.error) {
        console.log(`   Error: ${error.error}`);
      }
      if (error.errors && error.errors.length > 0) {
        console.log(`   Validation Errors: ${JSON.stringify(error.errors, null, 2)}`);
      }
    });
  }

  console.log('');

  // Exit with appropriate code
  process.exit(results.failed > 0 ? 1 : 0);
}

// Run tests if executed directly
if (require.main === module) {
  runAllTests().catch(error => {
    console.error('Test suite failed:', error);
    process.exit(1);
  });
}

module.exports = { runAllTests, testDiagram };
