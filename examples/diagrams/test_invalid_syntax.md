# Test Invalid Mermaid Syntax

This file contains definitely invalid Mermaid syntax that should fail parsing.

## Invalid Flowchart
```mermaid
flowchart TD
    A --> 
    --> B
    C ~~> D
    invalid syntax here
    E -> F{
```

## Invalid Sequence Diagram
```mermaid
sequenceDiagram
    participant A
    participant
    A->->->B: invalid arrows
    B<<--A: wrong direction
    random text
```