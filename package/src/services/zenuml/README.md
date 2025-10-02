# ZenUML Grammar Files

This directory contains the ANTLR4 grammar files for ZenUML sequence diagram parsing, extracted from the official [mermaid-js/zenuml-core](https://github.com/mermaid-js/zenuml-core) repository.

## Files

- `sequenceLexer.g4` - ANTLR4 lexer grammar for ZenUML tokenization
- `sequenceParser.g4` - ANTLR4 parser grammar for ZenUML syntax parsing

## Integration

ZenUML validation uses ANTLR4 (.g4) grammar files, unlike other diagram types that use either:
- **Jison grammars** (18 types in `src/services/grammars/`)  
- **Langium grammars** (7 types in `src/services/language/`)

## Usage

These grammar files are used by the ZenUML validator for syntax validation of ZenUML sequence diagrams. The validator currently uses basic validation fallback until full ANTLR4 integration is implemented.

## Source

Grammar files sourced from: https://github.com/mermaid-js/zenuml-core/tree/main/src/g4