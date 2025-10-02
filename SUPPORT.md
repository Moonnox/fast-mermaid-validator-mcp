# Support

This document outlines how to get support for the Mermaid Validator API project.

## Table of Contents

- [Getting Help](#getting-help)
- [Bug Reports](#bug-reports)
- [Feature Requests](#feature-requests)
- [Security Issues](#security-issues)
- [Response Times](#response-times)
- [Self-Service Resources](#self-service-resources)

## Getting Help

### Primary Contact

**Project Maintainer**: Gregorio Elias Roecker Momm
**Email**: gregoriomomm@gmail.com
**Role**: Lead Developer & Architect
**Location**: Brazil  

### Secondary Contacts

For escalation or when the primary contact is unavailable:
- **Team Lead**: [To be specified]
- **Management Chain**: [To be specified]
- **Technical Lead**: [To be specified]

## Bug Reports

### Before Reporting a Bug

1. Check existing issues to avoid duplicates
2. Review the documentation and FAQ
3. Ensure you're using the latest version
4. Try to reproduce the issue in a clean environment

### How to Report a Bug

Create an issue with the following information:

```markdown
## Bug Description
Brief description of the issue

## Environment
- Version: 
- Node.js Version:
- Operating System:
- Docker Version (if applicable):
- Kubernetes Version (if applicable):

## Steps to Reproduce
1. Step one
2. Step two
3. Step three

## Expected Behavior
What you expected to happen

## Actual Behavior
What actually happened

## Additional Context
- Error messages
- Log files
- Screenshots
- Configuration files (remove sensitive data)

## Impact
- Critical: System down, data loss
- High: Major functionality affected
- Medium: Minor functionality affected
- Low: Cosmetic or documentation issue
```


## Feature Requests

### Before Requesting a Feature

1. Check if the feature already exists
2. Review planned features in the roadmap
3. Consider if this fits the project scope
4. Think about the business value

### How to Request a Feature

Create an issue with:

```markdown
## Feature Request

### Description
Clear description of the requested feature

### Use Case
Why is this feature needed? What problem does it solve?

### Proposed Solution
How would you like this feature to work?

### Alternatives Considered
What other solutions have you considered?

### Additional Context
Any other context or screenshots about the feature request

### Business Impact
- Who would benefit from this feature?
- How critical is this feature?
- Are there workarounds available?
```

## Security Issues

### Reporting Security Vulnerabilities

**DO NOT** create public issues for security vulnerabilities.

Instead:

1. **Email directly**: gregoriomomm@gmail.com with subject "SECURITY: Mermaid Validator API"
2. **Include**:
   - Description of the vulnerability
   - Steps to reproduce
   - Potential impact
   - Suggested fix (if any)

### Security Response Process

1. **Acknowledgment**: Within 24 hours
2. **Initial Assessment**: Within 3 business days
3. **Resolution**: Based on severity
   - Critical: Immediate (same day)
   - High: 3-5 business days
   - Medium: 1-2 weeks
   - Low: Next release cycle

## Infrastructure Support

For deployment and infrastructure issues:
- **Kubernetes Issues**: Contact your platform team
- **Registry Issues**: Contact your container registry support
- **Network Issues**: Contact your network operations
- **Performance Issues**: Create a GitHub issue with performance details

## Response Times

### Standard Response Times

| Issue Type | Acknowledgment | Resolution Target |
|------------|----------------|-------------------|
| Critical Bug | 2 hours | 24 hours |
| High Priority Bug | 8 hours | 3 business days |
| Medium Priority Bug | 24 hours | 1 week |
| Low Priority Bug | 3 business days | 2 weeks |
| Feature Request | 1 week | Next release cycle |
| Documentation | 3 business days | 1 week |
| Security Issue | 2 hours | Based on severity |

### Business Hours

**Primary Support Hours**: 9:00 AM - 6:00 PM BRT (Brazil Time)  
**Emergency Contact**: Available for critical issues outside business hours

### Holiday and Vacation Coverage

- Coverage arrangements will be communicated in advance
- Emergency contacts will be provided during extended absences
- Critical issues will be escalated to backup contacts

## Self-Service Resources

### Documentation

- **[README.md](./README.md)**: Project overview and quick start
- **[DOCUMENTATION_INDEX.md](./DOCUMENTATION_INDEX.md)**: Complete documentation guide
- **[API Documentation](./README.md#api-endpoints)**: Comprehensive API reference
- **[Deployment Guide](./DEPLOYMENT.md)**: Deployment instructions
- **[Security Guide](./SECURITY.md)**: Security best practices

### Common Issues and Solutions

#### File Upload Issues
- **Problem**: "No files uploaded" error
- **Solution**: Ensure MIME type is set or use supported file extensions (.md, .txt, .zip)

#### Authentication Issues
- **Problem**: Unauthorized access
- **Solution**: Check API keys and permissions in Kubernetes deployment

#### Performance Issues
- **Problem**: Slow validation responses
- **Solution**: Check diagram complexity and consider chunking large files

#### Docker Issues
- **Problem**: Container startup failures
- **Solution**: Check permissions and volume mounts

### Monitoring and Health Checks

- **Health Endpoint**: `GET /api/v1/health`
- **Metrics**: Available through Prometheus endpoints
- **Logs**: Check application logs for detailed error information

### Development Setup

If you need to set up a development environment:

1. **Prerequisites**: Node.js 18+, Docker, Kubernetes access
2. **Installation**: `npm install`
3. **Configuration**: Copy `.env.example` to `.env`
4. **Running**: `npm run dev`

## Contributing

If you'd like to contribute to the project:
- Read [CONTRIBUTING.md](./CONTRIBUTING.md)
- Follow the [Code of Conduct](./CODE_OF_CONDUCT.md)
- Submit pull requests for review

## Feedback

We welcome feedback on:
- Documentation improvements
- User experience enhancements
- Feature suggestions
- Process improvements

**Feedback Channel**: gregoriomomm@gmail.com

---

**Document Version**: 1.0  
**Last Updated**: September 13, 2025  
**Next Review**: December 13, 2025

## Contact Summary

| Contact Type | Method | Response Time |
|--------------|--------|---------------|
| General Questions | gregoriomomm@gmail.com | 1 business day |
| Bug Reports | GitHub Issues | See response times above |
| Security Issues | Direct email (private) | 24 hours |
| Feature Requests | GitHub Issues | 1 week |
| Emergency | Direct email + escalation | 2 hours |