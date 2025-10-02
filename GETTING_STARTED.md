# Getting Started

Welcome to the Mermaid Validator API! This guide will help you get up and running quickly.

## Overview

The Mermaid Validator API is a comprehensive validation service for Mermaid diagrams that supports all 26+ diagram types. It provides both REST API endpoints and file upload capabilities for validating Mermaid syntax and generating detailed error reports.

## Prerequisites

- **Node.js**: Version 18.0 or higher
- **npm**: Version 8.0 or higher
- **Docker**: Optional, for containerized deployment

## Quick Start

### 1. Install from NPM (Recommended)

```bash
npm install @ai-of-mine/mermaid-validator-mcp
```

### 2. Or Install from Source

```bash
git clone https://github.com/ai-of-mine/fast-mermaid-validator-mcp.git
cd mermaid-validator-mcp
npm install
```

### 3. Build the Project

```bash
npm run build
```

### 4. Start the Server

```bash
npm start
```

The API will be available at `http://localhost:8000`

### 5. Test the API

**Health Check:**
```bash
curl http://localhost:8000/api/v1/health
```

**Validate a Diagram:**
```bash
curl -X POST http://localhost:8000/api/v1/validate \
  -H "Content-Type: application/json" \
  -d '{"content": "flowchart TD\n    A-->B"}'
```

**Upload a File:**
```bash
curl -F "file=@examples/diagrams/simple_test.md" \
  http://localhost:8000/api/v1/upload/file
```

## API Documentation

Interactive API documentation is available at:
- **Swagger UI**: `http://localhost:8000/docs`

## Supported Diagram Types

The API validates all Mermaid diagram types including:
- Flowcharts, Sequence Diagrams, Class Diagrams
- State Diagrams, ER Diagrams, Gantt Charts
- User Journey, Git Graph, Pie Charts
- And 17+ additional diagram types

## Docker Deployment

```bash
# Build the image
docker build -t mermaid-validator-mcp .

# Run the container
docker run -p 8000:8000 mermaid-validator-mcp
```

## Configuration

The application can be configured through environment variables:

- `PORT`: Server port (default: 8000)
- `NODE_ENV`: Environment (development/production)
- `LOG_LEVEL`: Logging level (info/debug/error)

## Development

### Running Tests

```bash
# Run all tests
npm test

# Run specific test suites
npm run test:unit
npm run test:integration
```

### Linting

```bash
npm run lint
npm run lint:fix
```

### Building Grammars

```bash
npm run build:grammars
```

## Contributing

We welcome contributions! Please see:
- [CONTRIBUTING.md](./CONTRIBUTING.md) for contribution guidelines
- [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md) for community standards

### Development Workflow

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests and linting
5. Submit a pull request

## Support

- **Documentation**: See [DOCUMENTATION_INDEX.md](./DOCUMENTATION_INDEX.md)
- **Issues**: Report bugs or request features via GitHub Issues
- **Security**: See [SECURITY.md](./SECURITY.md) for security reporting

## Architecture

For detailed technical information:
- [PARSER_ARCHITECTURE.md](./PARSER_ARCHITECTURE.md) - Grammar parsing system
- [DEPLOYMENT.md](./DEPLOYMENT.md) - Deployment guides
- [ENVIRONMENT.md](./ENVIRONMENT.md) - Configuration details

## License

This project is licensed under the terms specified in [LICENSE](./LICENSE).

---

**Need help?** Check the [SUPPORT.md](./SUPPORT.md) file or open an issue.