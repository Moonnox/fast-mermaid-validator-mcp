# Contributing to Mermaid Validator API

We welcome contributions to the Mermaid Validator API project! This document provides guidelines for contributing to this open source project.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [Making Changes](#making-changes)
- [Submitting Changes](#submitting-changes)
- [Coding Standards](#coding-standards)
- [Testing Guidelines](#testing-guidelines)
- [Documentation](#documentation)
- [Support](#support)


## Code of Conduct

This project adheres to the Apache Code of Conduct. By participating, you are expected to uphold this code. Please report unacceptable behavior to the project maintainers.

## Getting Started

### Prerequisites

- Node.js 18.x or later
- Docker and Docker Compose
- Kubernetes cluster access (for deployment testing)
- Container registry access (if deploying)

### Initial Setup

1. Fork the repository (if applicable for your contribution workflow)
2. Clone your fork or the main repository:
   ```bash
   git clone <repository-url>
   cd mermaid-validator-mcp
   ```
3. Install dependencies:
   ```bash
   npm install
   ```
4. Set up environment variables:
   ```bash
   cp .env.example .env
   # Edit .env with your configuration
   ```

## Development Setup

### Local Development

```bash
# Install dependencies
npm install

# Start development server
npm run dev

# Run in Docker
docker-compose --profile dev up
```

### Environment Configuration

Required environment variables:
- `NODE_ENV`: development/production
- `PORT`: Server port (default: 8000)
- `LOG_LEVEL`: Logging level (default: info)

See `ENVIRONMENT.md` for complete configuration details.

## Making Changes

### Branch Strategy

- `main`: Production-ready code
- `develop`: Integration branch for features
- `feature/*`: Feature development branches
- `bugfix/*`: Bug fix branches
- `hotfix/*`: Critical production fixes

### Workflow

1. Create a feature branch from `develop`:
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/your-feature-name
   ```

2. Make your changes following our coding standards
3. Write or update tests for your changes
4. Update documentation as needed
5. Test your changes thoroughly

### Commit Guidelines

We follow the Conventional Commits specification:

```
type(scope): description

[optional body]

[optional footer(s)]
```

Types:
- `feat`: New features
- `fix`: Bug fixes
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

Examples:
```
feat(api): add support for new diagram type
fix(security): resolve file upload vulnerability
docs(readme): update installation instructions
```

## Submitting Changes

### Pull Request Process

1. Ensure your branch is up to date with `develop`
2. Run the full test suite:
   ```bash
   npm test
   npm run lint
   npm run security
   ```
3. Create a pull request with:
   - Clear title and description
   - Reference to related issues
   - Screenshots/examples if applicable
   - Test evidence

### Pull Request Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests pass
- [ ] Integration tests pass
- [ ] Manual testing completed

## Checklist
- [ ] Code follows project standards
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] No new security vulnerabilities
```

## Coding Standards

### JavaScript/Node.js Standards

- Use ES6+ features where appropriate
- Follow ESLint configuration
- Use JSDoc for function documentation
- Implement proper error handling
- Follow security best practices

### Security Requirements

- Validate all inputs
- Use parameterized queries
- Implement proper authentication/authorization
- Follow OWASP guidelines
- Run security scans before submission

### Code Style

```javascript
// Use descriptive variable names
const validationResults = await validator.validateDiagrams(diagrams);

// Implement proper error handling
try {
  const result = await processFile(file);
  return result;
} catch (error) {
  logger.error('File processing failed', { error: error.message, file: file.name });
  throw new ProcessingError('Failed to process file', { cause: error });
}

// Use JSDoc for documentation
/**
 * Validates multiple Mermaid diagrams
 * @param {Array<Object>} diagrams - Array of diagram objects
 * @param {Object} options - Validation options
 * @returns {Promise<Object>} Validation results
 */
async function validateDiagrams(diagrams, options = {}) {
  // Implementation
}
```

## Testing Guidelines

### Test Types

1. **Unit Tests**: Test individual functions and methods
2. **Integration Tests**: Test API endpoints and components
3. **Security Tests**: Test for vulnerabilities
4. **Performance Tests**: Validate performance requirements

### Running Tests

```bash
# Run all tests
npm test

# Run specific test suites
npm run test:unit
npm run test:integration
npm run test:security

# Run with coverage
npm run test:coverage

# Watch mode for development
npm run test:watch
```

### Writing Tests

```javascript
describe('Diagram Validator', () => {
  describe('validateDiagram', () => {
    it('should validate a correct flowchart diagram', async () => {
      const diagram = {
        content: 'flowchart TD\n    A --> B',
        type: 'flowchart'
      };
      
      const result = await validator.validateDiagram(diagram);
      
      expect(result.valid).toBe(true);
      expect(result.errors).toHaveLength(0);
    });

    it('should reject invalid syntax', async () => {
      const diagram = {
        content: 'flowchart TD\n    A --> -->',
        type: 'flowchart'
      };
      
      const result = await validator.validateDiagram(diagram);
      
      expect(result.valid).toBe(false);
      expect(result.errors).toHaveLength(1);
    });
  });
});
```

## Documentation

### Documentation Standards

- Update README.md for user-facing changes
- Update API documentation for endpoint changes
- Include JSDoc comments for all public functions
- Update CHANGELOG.md with notable changes
- Provide examples for new features

### Documentation Structure

- `README.md`: Project overview and quick start
- `DOCUMENTATION_INDEX.md`: Navigation guide
- `API.md`: Detailed API documentation
- `DEPLOYMENT.md`: Deployment instructions
- `SECURITY.md`: Security guidelines

## Support

### Getting Help

- **Technical Questions**: Contact the project maintainer
- **Bug Reports**: Create an issue with detailed reproduction steps
- **Feature Requests**: Create an issue with use case description
- **Security Issues**: Report privately to project maintainers

### Contact Information

- **Project Maintainer**: Gregorio Elias Roecker Momm (gregoriomomm@gmail.com)
- **Repository**: https://github.com/ai-of-mine/fast-mermaid-validator-mcp

### Response Times

- Bug reports: 2-3 business days
- Feature requests: 1 week for initial review
- Security issues: 24 hours for acknowledgment

## Recognition

Contributors will be recognized in:
- CONTRIBUTORS.md file
- Release notes for significant contributions
- Project documentation

Thank you for contributing to the Mermaid Validator API project!

---

**Document Version**: 1.0  
**Last Updated**: September 13, 2025  
**Author**: Gregorio Elias Roecker Momm  
**Contact**: gregoriomomm@gmail.com