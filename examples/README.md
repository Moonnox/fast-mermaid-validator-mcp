# Examples

This directory contains example Mermaid diagrams for testing and demonstration purposes.

## Directory Structure

- **`diagrams/`** - Collection of Mermaid diagram examples covering all 28 supported diagram types

## Example Files

### Comprehensive Test Suite
- `all_26_diagram_types.md` - Complete test suite with examples of all 28 diagram types
- `comprehensive_test.md` - Additional comprehensive testing scenarios

### Real-world Examples
- `dad_child3.md` - Complex business process diagram
- `dad_child3_original.md` - Original version of business process
- `lgicus01_fixed.md` - Fixed logistics diagram example
- `lgtestp2_fixed.md` - Fixed logistics test example

### Basic Examples
- `simple_test.md` - Simple flowchart example
- `simple_flowchart_test.md` - Basic flowchart test
- `sequence_test.md` - Sequence diagram example
- `test_diagram.mmd` - Test diagram in .mmd format

### Error Testing
- `invalid_sequence.md` - Invalid syntax examples for testing error handling
- `test_invalid_syntax.md` - Additional invalid syntax tests

## Usage

### Test via API

```bash
# Upload and validate a file
curl -X POST http://localhost:8000/api/v1/upload/file \
  -F "file=@examples/diagrams/all_26_diagram_types.md;type=text/markdown"

# Validate diagram content directly
curl -X POST http://localhost:8000/api/v1/validate \
  -H "Content-Type: application/json" \
  -d '{"content": "flowchart TD\n    A-->B"}'
```

### Test via MCP

```bash
# Start MCP server
npm run start:mcp-http

# Use MCP tools to validate diagrams
# See MCP documentation for details
```

## Supported Diagram Types (28 Total)

1. Flowchart
2. Sequence Diagram
3. Class Diagram
4. State Diagram
5. Entity Relationship Diagram
6. Gantt Chart
7. User Journey
8. Requirement Diagram
9. Sankey Diagram
10. XY Chart
11. Kanban
12. Block Diagram
13. C4 Diagram
14. Mindmap
15. Quadrant Chart
16. Timeline
17. Git Graph
18. Pie Chart
19. Info (basic text)
20. Architecture
21. Radar Chart
22. Packet Diagram
23. Treemap
24. Zenuml
25. BPMN (via plugin)
26. Excalidraw (via plugin)
27. MindElixir (via plugin)
28. Custom diagrams (via plugin)

## Adding New Examples

To add new example diagrams:

1. Create a `.md` or `.mmd` file in the `diagrams/` directory
2. Include Mermaid code blocks with diagram type specified
3. Add descriptive comments explaining the diagram
4. Test the diagram using the API or MCP tools

## Note

These examples are used for:
- Testing all supported diagram types
- Demonstrating API capabilities
- Regression testing
- Documentation examples
