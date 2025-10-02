/**
 * Direct test bypassing the API to show the working SVG generation
 */

const MermaidToSVGConverter = require('./src/services/mermaidToSvgConverter');

async function testDirect() {
  const converter = new MermaidToSVGConverter();

  // Use the exact format that worked in our debug test
  const diagram = `graph TD
    A[Start] --> B{Decision}
    B -->|Yes| C[Action 1]
    B -->|No| D[Action 2]
    C --> E[End]
    D --> E`;

  try {
    console.log('🔄 Converting diagram...');
    const result = await converter.convertToSVG(diagram, {
      theme: 'forest',
      layout: 'hierarchical'
    });

    if (result.success) {
      console.log('✅ SVG Generated successfully!');
      console.log('📊 Metadata:', {
        nodeCount: result.metadata.nodeCount,
        edgeCount: result.metadata.edgeCount,
        theme: result.metadata.theme,
        processingTime: result.metadata.processingTime + 'ms'
      });

      // Save to file
      require('fs').writeFileSync('direct-test.svg', result.svg);
      console.log('💾 SVG saved to: direct-test.svg');

      // Show first part of SVG
      console.log('🎨 SVG Preview (first 200 chars):');
      console.log(result.svg.substring(0, 200) + '...');

    } else {
      console.error('❌ Conversion failed:', result.error);
    }
  } catch (error) {
    console.error('💥 Error:', error.message);
  }
}

testDirect();