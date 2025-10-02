# Invalid Sequence Diagram Test

```mermaid
sequenceDiagram
    participant A as Alice
    participant B as Bob
    
    A->>B: Hello Bob
    B-->>A: Hello Alice
    
    # This is invalid syntax - should cause parse error
    A->->->B: Invalid arrow syntax
    participant: Invalid participant syntax
    random invalid text here
    B<<--A: Wrong arrow direction
```