# API Response Format Documentation

## Response Structure

### Valid Diagram Response

```json
{
  "totalDiagrams": 1,
  "validDiagrams": 1,
  "invalidDiagrams": 0,
  "results": [
    {
      "diagramId": "diagram_1",
      "valid": true,
      "errors": [],
      "warnings": [],
      "svgGenerated": false,
      "metadata": {
        "diagramType": "flowchart",
        "validationMethod": "real_jison_grammar",
        "contentLength": 53,
        "lineCount": 3,
        "customValidator": true,
        "processingTime": 1
      }
    }
  ],
  "processingTime": 2,
  "validator": "custom_grammar_parser",
  "requestId": "uuid-here",
  "timestamp": "2025-09-19T18:00:00.000Z"
}
```

### Invalid Diagram Response (WITH LLM Syntax Rules)

```json
{
  "totalDiagrams": 1,
  "validDiagrams": 0,
  "invalidDiagrams": 1,
  "results": [
    {
      "diagramId": "diagram_1",
      "valid": false,
      "errors": [
        {
          "type": "syntax_error",
          "message": "Parse error on line 2: Invalid node ID '1Start' (cannot start with number)",
          "line": 2,
          "column": 5
        }
      ],
      "warnings": [],
      "svgGenerated": false,
      "metadata": {
        "diagramType": "flowchart",
        "validationMethod": "real_jison_grammar",
        "contentLength": 95,
        "lineCount": 4,
        "customValidator": true,
        "processingTime": 3
      },
      "applicableSyntax": "MERMAID FLOWCHART SYNTAX RULES:\n\nGENERAL RULES:\n- Node IDs: Must start with letter, contain only letters/numbers/underscores\n- Text with spaces: Must be quoted [\"text with spaces\"]\n- Line breaks: Use <br/> tags, never literal breaks\n- Special characters: Quote all text containing (){}[]$%@#&*\n\nFLOWCHART SPECIFIC RULES:\n- Declaration: flowchart TD (or LR, BT, RL)\n- Node shapes: [text] = Rectangle, ([text]) = Stadium/Pill, {text} = Rhombus/Diamond, [(text)] = Database, [[text]] = Subroutine, ((text)) = Circle\n- Arrows: --> = Standard arrow, -.-> = Dotted arrow, ==> = Thick arrow, -->|text| = Labeled arrow\n\nCOMMON ISSUES DETECTED:\n- Node IDs contain invalid characters: Replace with valid identifiers (letters, numbers, underscores only)\n\nFIX REQUIREMENTS:\n1. Preserve original meaning and logic flow\n2. Fix all syntax errors while maintaining structure\n3. Use proper flowchart declaration and syntax\n4. Ensure all identifiers follow naming rules"
    }
  ],
  "processingTime": 5,
  "validator": "custom_grammar_parser",
  "requestId": "uuid-here",
  "timestamp": "2025-09-19T18:00:00.000Z"
}
```

## LLM Integration

### Developer Usage

For invalid diagrams, use the `applicableSyntax` field directly in LLM prompts:

```javascript
// Extract syntax rules from API response
const syntaxRules = response.results.find(r => !r.valid)?.applicableSyntax;

// Create LLM prompt for diagram fixing
const fixPrompt = `Fix this invalid Mermaid diagram:

INVALID DIAGRAM:
${invalidDiagramContent}

SYNTAX RULES TO FOLLOW:
${syntaxRules}

CORRECTED DIAGRAM:`;

// Send to your LLM service (OpenAI, Claude, etc.)
const fixedDiagram = await llmService.complete(fixPrompt);
```

### Supported Diagram Types

The `applicableSyntax` field provides type-specific syntax rules for all supported diagram types:

#### Basic Diagrams
- **flowchart/graph**: Node shapes, arrows, connections
- **sequenceDiagram**: Participants, messages, activations
- **classDiagram**: Classes, methods, inheritance, visibility
- **stateDiagram**: States, transitions, parallel states
- **erDiagram**: Entities, relationships, cardinality notation

#### Advanced Diagrams
- **gantt**: Tasks, dependencies, milestones
- **journey**: User journeys, sections, tasks
- **requirement**: Requirements, relationships, risk levels
- **mindmap**: Hierarchical mind maps
- **timeline**: Timeline events and periods

#### Specialized Diagrams
- **c4/C4Context**: C4 model contexts, containers, components
- **block**: Block diagrams with connections
- **quadrant**: Quadrant charts with data points
- **sankey**: Sankey diagrams with flows
- **xychart**: XY charts with data series

### Example Syntax Rules by Type

#### ER Diagram Rules
```text
MERMAID ERDIAGRAM SYNTAX RULES:

GENERAL RULES:
- Node IDs: Must start with letter, contain only letters/numbers/underscores
- Text with spaces: Must be quoted ["text with spaces"]

ERDIAGRAM SPECIFIC RULES:
- Declaration: erDiagram
- Relationships: ||--|| = One to one, ||--o{ = One to many, o{--o{ = Many to many
- Constraints: PK, FK, UK
- Entity syntax: ENTITY_NAME { datatype column_name constraints }
```

#### Flowchart Rules
```text
MERMAID FLOWCHART SYNTAX RULES:

GENERAL RULES:
- Node IDs: Must start with letter, contain only letters/numbers/underscores
- Text with spaces: Must be quoted ["text with spaces"]

FLOWCHART SPECIFIC RULES:
- Declaration: flowchart TD (or LR, BT, RL)
- Node shapes: [text] = Rectangle, {text} = Diamond, ([text]) = Stadium
- Arrows: --> = Standard arrow, -.-> = Dotted arrow, -->|text| = Labeled arrow
```

## Error Handling

### Validation Errors

Each error includes:
- **type**: Error category (syntax_error, validation_error, etc.)
- **message**: Detailed error description
- **line**: Line number where error occurred
- **column**: Column position (when available)

### LLM-Friendly Responses

Only invalid diagrams receive the `applicableSyntax` field. Valid diagrams return:
```json
{
  "valid": true,
  "errors": [],
  "applicableSyntax": null
}
```

This approach:
- **Reduces response size** for valid diagrams
- **Provides context** only when needed
- **Enables automated fixing** through LLM integration
- **Maintains clean API design** without unnecessary data