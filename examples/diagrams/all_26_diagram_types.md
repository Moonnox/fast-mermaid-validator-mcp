# Complete Test Suite - All 26 Mermaid Diagram Types

This document contains examples of all 26 supported diagram types for comprehensive validation testing.

## Jison Grammar Parsers (18 types)

### 1. Flowchart
```mermaid
flowchart TD
    A[Start] --> B{Decision}
    B -->|Yes| C[Process 1]
    B -->|No| D[Process 2]
    C --> E[End]
    D --> E
```

### 2. Sequence Diagram
```mermaid
sequenceDiagram
    participant A as Alice
    participant B as Bob
    A->>B: Hello Bob
    B-->>A: Hello Alice
    A->>B: How are you?
    B-->>A: I'm good, thanks!
```

### 3. Class Diagram
```mermaid
classDiagram
    class Animal {
        +String name
        +int age
        +makeSound()
    }
    class Dog {
        +String breed
        +bark()
    }
    Animal <|-- Dog
```

### 4. State Diagram
```mermaid
stateDiagram-v2
    [*] --> Still
    Still --> [*]
    Still --> Moving
    Moving --> Still
    Moving --> Crash
    Crash --> [*]
```

### 5. Entity Relationship Diagram
```mermaid
erDiagram
    CUSTOMER ||--o{ ORDER : places
    ORDER ||--|{ LINE-ITEM : contains
    CUSTOMER }|..|{ DELIVERY-ADDRESS : uses
```

### 6. Gantt Chart
```mermaid
gantt
    title Project Schedule
    dateFormat YYYY-MM-DD
    section Planning
    Research    :2024-01-01, 30d
    Design      :2024-02-01, 20d
```

### 7. User Journey
```mermaid
journey
    title User Registration Journey
    section Sign Up
      Visit site: 5: Me
      Click register: 3: Me
      Fill form: 1: Me
      Submit: 2: Me
```

### 8. Requirement Diagram
```mermaid
requirementDiagram
    requirement test_req {
        id: 1
        text: the test text.
        risk: high
        verifymethod: test
    }
```

### 9. Sankey Diagram
```mermaid
sankey-beta
    Agricultural 'waste',Cattle,2
    Cattle,Meat,1
    Cattle,Dairy,1
```

### 10. XY Chart
```mermaid
xychart-beta
    title "Sales Revenue"
    x-axis [Jan, Feb, Mar, Apr, May]
    y-axis "Revenue (in $)" 4000 --> 11000
    bar [5000, 6000, 7500, 8200, 9500]
```

### 11. Kanban Board
```mermaid
kanban
    Todo
    ["Task 1"]
    ["Task 2"]
    
    Doing
    ["Task 3"]
    
    Done
    ["Task 4"]
```

### 12. Quadrant Chart
```mermaid
quadrant
    title Reach and influence
    x-axis Low Reach --> High Reach
    y-axis Low Influence --> High Influence
    quadrant-1 We should expand
    quadrant-2 Need to promote
    quadrant-3 Re-evaluate
    quadrant-4 May be improved
```

### 13. C4 Context Diagram
```mermaid
c4Context
    title System Context diagram for Internet Banking System
    Enterprise_Boundary(eb, "Banking Enterprise") {
        Person(customer, "Banking Customer", "A customer")
        System(banking_system, "Internet Banking System", "Allows customers to view information")
    }
```

### 14. Mindmap
```mermaid
mindmap
  root((Project))
    Planning
      Requirements
      Timeline
    Development
      Frontend
      Backend
    Testing
      Unit Tests
      Integration
```

### 15. Timeline
```mermaid
timeline
    title History of Social Media Platform
    2002 : LinkedIn
    2004 : Facebook : Google
    2005 : Youtube
    2006 : Twitter
```

### 16. Block Diagram
```mermaid
block-beta
    columns 3
    A["Input"] B["Process"] C["Output"]
    A --> B
    B --> C
```

## Langium Grammar Parsers (8 types)

### 17. Pie Chart
```mermaid
pie title Pet Sales
    "Dogs" : 386
    "Cats" : 85
    "Rats" : 15
```

### 18. Git Graph
```mermaid
gitgraph
    commit
    commit
    branch develop
    checkout develop
    commit
    checkout main
    merge develop
```

### 19. Info Diagram
```mermaid
info
    showInfo
```

### 20. Architecture Diagram
```mermaid
architecture-beta
    group api(cloud)[API]
    service db(database)[Database] in api
    service web(server)[Web Server] in api
    web:R --> L:db
```

### 21. Radar Chart
```mermaid
radar
    title Skill Assessment
    "Communication" [0.8]
    "Technical" [0.9]
    "Leadership" [0.6]
    "Problem Solving" [0.85]
```

### 22. Packet Diagram
```mermaid
packet-beta
    0-15: "Source Port"
    16-31: "Destination Port"
    32-63: "Sequence Number"
```

### 23. Treemap
```mermaid
treemap
    title "Sales by Region"
    "North America" 40
    "Europe" 30
    "Asia" 20
    "Others" 10
```

## Additional Missing Types (Need Grammar Files)

### 24. Graph (Alternative Flowchart)
```mermaid
graph TD
    A --> B
    A --> C
    B --> D
    C --> D
```

### 25. Journey (Alternative User Journey)
```mermaid
journey
    title My working day
    section Go to work
      Make tea: 5: Me
      Go upstairs: 3: Me
      Do work: 1: Me, Cat
```

### 26. Flowchart LR
```mermaid
flowchart LR
    A[Start] --> B[Process]
    B --> C[End]
```

## Test Validation

To test all parsers through the upload endpoint:

```bash
# Test this comprehensive file
curl -X POST http://localhost:8000/api/v1/upload/file \
  -F 'file=@example/all_26_diagram_types.md;type=text/markdown' \
  | jq '.'

# Expected results:
# - totalDiagrams: 26
# - validDiagrams: 18 (Jison parsers working)
# - invalidDiagrams: 8 (Langium parsers need setup)
```

## Parser Status Check

Use the stats endpoint to verify parser availability:

```bash
curl http://localhost:8000/api/v1/validate/stats | jq '.supportedDiagramTypes'
```

This should return information about all 26 supported diagram types and their current validation status.