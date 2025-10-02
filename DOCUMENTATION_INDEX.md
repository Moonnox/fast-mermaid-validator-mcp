# Mermaid Validator MCP Server - Documentation Index

This document provides a comprehensive overview of all documentation available for the Mermaid Validator MCP Server project.

## 📚 Core Documentation

### Getting Started
- **[README.md](README.md)** - Main project overview and quick start guide
- **[GETTING_STARTED.md](GETTING_STARTED.md)** - Detailed setup and configuration instructions

### MCP Server Documentation
- **[MCP API Documentation](docs/api/README-MCP.md)** - Complete MCP server API reference and usage guide

## 🏗 Architecture & Development

### Architecture Documentation
- **[Parser Architecture](docs/architecture/PARSER_ARCHITECTURE.md)** - Grammar parser system design and implementation
- **[Project Structure](docs/guides/PROJECT_STATUS.md)** - Current project status and component overview

### Development Guides
- **[Build Instructions](docs/guides/BUILD_INSTRUCTIONS.md)** - How to build and compile the project
- **[Validation Summary](docs/guides/VALIDATION_SUMMARY.md)** - Comprehensive validation capabilities overview

## 🚀 Deployment & Operations

### Deployment Documentation
- **[Deployment Guide](docs/deployment/DEPLOYMENT.md)** - Production deployment instructions
- **[Environment Configuration](docs/deployment/ENVIRONMENT.md)** - Environment variables and configuration options

## 📋 Examples & Testing

### Example Diagrams
- **[Example Diagrams](examples/diagrams/)** - Sample Mermaid diagrams for testing and reference
  - All 28 supported diagram types
  - Valid and invalid syntax examples
  - Comprehensive test cases

### Testing
- **[MCP Integration Tests](tests/mcp/test-mcp-integration.js)** - Automated MCP server functionality tests

## 🔒 Security & Compliance

### Security Documentation
- **[SECURITY.md](SECURITY.md)** - Security policies and vulnerability reporting
- **[Code of Conduct](CODE_OF_CONDUCT.md)** - Community guidelines and behavior expectations

### Compliance & Legal
- **[License Information](LICENSE)** - Project licensing terms
- **[Contributing Guidelines](CONTRIBUTING.md)** - How to contribute to the project
- **[Support Information](SUPPORT.md)** - How to get help and support

## 🤝 Community & Collaboration

### Project Governance
- **[Starter Team Information](STARTER_TEAM.md)** - Initial development team and responsibilities
- **[Pull Request Template](.github/pull_request_template.md)** - PR submission guidelines
- **[Code Owners](CODEOWNERS)** - Project maintainers and code review assignments

## 🔧 Configuration Files

### Build & Development Configuration
- **[Package Configuration](package.json)** - Node.js dependencies and scripts
- **[TypeScript Configuration](tsconfig.json)** - TypeScript compiler settings
- **[TypeScript MCP Configuration](tsconfig.mcp.json)** - MCP-specific TypeScript settings

### Quality & Security Configuration
- **[ESLint Configuration](.eslintrc.js)** - Code linting rules
- **[Audit Configuration](audit-ci.json)** - Security audit settings
- **[Langium Configuration](langium-config.json)** - Grammar parser configuration

## 📊 Project Status

### Current Status
- ✅ **Core Functionality**: Complete validation system with 28 diagram types
- ✅ **MCP Server**: Full Model Context Protocol implementation
- ✅ **Multiple Transports**: Stdio, HTTP, and Secure HTTP transports
- ✅ **Security Features**: Production-ready security and audit features
- ✅ **Documentation**: Comprehensive documentation suite

### Architecture Overview
```
mermaid-validator-mcp/
├── src/mcp/                    # MCP server implementation
│   ├── server.ts              # Core MCP server (stdio)
│   ├── server-http.ts         # HTTP transport server
│   ├── server-secure.ts       # Secure production server
│   ├── tools/                 # MCP tool implementations
│   ├── transports/            # Transport layer implementations
│   ├── middleware/            # Security middleware
│   └── schemas/               # Validation schemas
├── src/services/              # Core validation services
├── docs/                      # Organized documentation
├── examples/diagrams/         # Example Mermaid diagrams
├── tests/mcp/                 # MCP-specific tests
└── dist/                      # Compiled TypeScript output
```

For specific implementation details, refer to the individual documentation files linked above.