# Environment Variables Configuration

Complete documentation of all environment variables for the Mermaid Validator API.

## 🌐 Server Configuration

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `PORT` | Integer | `8000` | HTTP server port |
| `HOST` | String | `0.0.0.0` | HTTP server host binding |
| `NODE_ENV` | String | `development` | Node.js environment (`development`, `production`, `test`) |
| `API_VERSION` | String | `v1` | API version prefix |
| `MAX_REQUEST_SIZE` | String | `50mb` | Maximum request body size |

**Example:**
```bash
PORT=8080
HOST=127.0.0.1
NODE_ENV=production
API_VERSION=v1
MAX_REQUEST_SIZE=100mb
```

## 🔒 Security Configuration

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `CORS_ORIGIN` | String | `*` | CORS allowed origins (comma-separated or `*`) |
| `RATE_LIMIT_WINDOW_MS` | Integer | `900000` | Rate limit window in milliseconds (15 minutes) |
| `RATE_LIMIT_MAX_REQUESTS` | Integer | `100` | Maximum requests per IP per window |
| `HELMET_CSP_ENABLED` | Boolean | `true` | Enable Content Security Policy headers |
| `TRUST_PROXY` | Boolean | `true` | Trust proxy headers for IP detection |

**Example:**
```bash
CORS_ORIGIN="https://app.example.com,https://admin.example.com"
RATE_LIMIT_WINDOW_MS=300000
RATE_LIMIT_MAX_REQUESTS=50
HELMET_CSP_ENABLED=true
TRUST_PROXY=true
```

## 📁 File Upload Configuration

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `MAX_FILE_SIZE` | Integer | `10485760` | Maximum file size in bytes (10MB) |
| `MAX_FILES` | Integer | `20` | Maximum number of files per request |
| `ALLOWED_MIME_TYPES` | String | See config | Comma-separated list of allowed MIME types |
| `ALLOWED_EXTENSIONS` | String | `.md,.markdown,.txt,.zip` | Comma-separated list of allowed file extensions |
| `TEMP_DIR` | String | `./tmp` | Temporary directory for file uploads |
| `CLEANUP_INTERVAL` | Integer | `3600000` | Temp file cleanup interval in milliseconds (1 hour) |

**Example:**
```bash
MAX_FILE_SIZE=20971520  # 20MB
MAX_FILES=50
ALLOWED_MIME_TYPES="text/markdown,text/plain,application/zip"
ALLOWED_EXTENSIONS=".md,.markdown,.txt,.zip"
TEMP_DIR="/app/temp"
CLEANUP_INTERVAL=1800000  # 30 minutes
```

## ✅ Validation Configuration

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `VALIDATION_TIMEOUT` | Integer | `30000` | Validation timeout in milliseconds (30 seconds) |
| `MAX_DIAGRAMS_PER_FILE` | Integer | `50` | Maximum diagrams allowed per file |
| `MAX_TOTAL_DIAGRAMS` | Integer | `200` | Maximum total diagrams per request |
| `ENABLE_SVG_GENERATION` | Boolean | `true` | Enable SVG generation for valid diagrams |
| `SVG_MAX_SIZE` | Integer | `100000` | Maximum SVG size to store in response (100KB) |
| `VALIDATION_METHOD` | String | `pure` | Validation method (`pure` or `browser`) |

**Example:**
```bash
VALIDATION_TIMEOUT=60000  # 1 minute
MAX_DIAGRAMS_PER_FILE=100
MAX_TOTAL_DIAGRAMS=500
ENABLE_SVG_GENERATION=true
SVG_MAX_SIZE=200000
VALIDATION_METHOD=pure
```

## 📊 Logging Configuration

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `LOG_LEVEL` | String | `info` | Logging level (`error`, `warn`, `info`, `debug`) |
| `LOG_FORMAT` | String | `combined` | Log format (`combined`, `common`, `dev`, `short`, `tiny`) |
| `LOG_TO_FILE` | Boolean | `false` | Enable file logging |
| `LOG_FILE` | String | `logs/app.log` | Log file path |
| `LOG_MAX_FILES` | Integer | `5` | Maximum number of log files to keep |
| `LOG_MAX_SIZE` | String | `10m` | Maximum log file size |

**Example:**
```bash
LOG_LEVEL=debug
LOG_FORMAT=json
LOG_TO_FILE=true
LOG_FILE="/var/log/mermaid-validator/app.log"
LOG_MAX_FILES=10
LOG_MAX_SIZE=50m
```

## 🏥 Health Check Configuration

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `MEMORY_THRESHOLD` | Integer | `90` | Memory usage threshold percentage for health checks |
| `DISK_THRESHOLD` | Integer | `90` | Disk usage threshold percentage for health checks |
| `HEALTH_CHECK_INTERVAL` | Integer | `30000` | Health check interval in milliseconds |
| `READINESS_TIMEOUT` | Integer | `5000` | Readiness probe timeout in milliseconds |

**Example:**
```bash
MEMORY_THRESHOLD=85
DISK_THRESHOLD=85
HEALTH_CHECK_INTERVAL=60000
READINESS_TIMEOUT=10000
```

## 🎨 Mermaid Configuration

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `MERMAID_THEME` | String | `default` | Default Mermaid theme (`default`, `dark`, `forest`, `neutral`) |
| `MERMAID_LOG_LEVEL` | String | `error` | Mermaid internal log level |
| `MERMAID_SECURITY_LEVEL` | String | `strict` | Mermaid security level (`strict`, `loose`, `antiscript`) |
| `MERMAID_MAX_TEXT_SIZE` | Integer | `50000` | Maximum text size for Mermaid diagrams |
| `MERMAID_MAX_EDGES` | Integer | `500` | Maximum number of edges in diagrams |
| `MERMAID_MAX_VERTICES` | Integer | `200` | Maximum number of vertices in diagrams |

**Example:**
```bash
MERMAID_THEME=dark
MERMAID_LOG_LEVEL=warn
MERMAID_SECURITY_LEVEL=strict
MERMAID_MAX_TEXT_SIZE=100000
MERMAID_MAX_EDGES=1000
MERMAID_MAX_VERTICES=500
```

## 🚀 Production Environment Variables

### Minimal Production Configuration
```bash
# Required
NODE_ENV=production
PORT=8000
HOST=0.0.0.0

# Security
CORS_ORIGIN="https://yourdomain.com"
RATE_LIMIT_MAX_REQUESTS=50
RATE_LIMIT_WINDOW_MS=900000

# File Limits
MAX_FILE_SIZE=10485760
MAX_FILES=10
MAX_TOTAL_DIAGRAMS=100

# Logging
LOG_LEVEL=warn
LOG_TO_FILE=true
LOG_FILE=/var/log/mermaid-validator/app.log

# Health
MEMORY_THRESHOLD=80
DISK_THRESHOLD=80
```

### High-Performance Production Configuration
```bash
# Performance optimized
NODE_ENV=production
PORT=8000
HOST=0.0.0.0
MAX_REQUEST_SIZE=100mb

# Aggressive rate limiting
RATE_LIMIT_MAX_REQUESTS=200
RATE_LIMIT_WINDOW_MS=300000

# Higher limits for enterprise use
MAX_FILE_SIZE=52428800  # 50MB
MAX_FILES=50
MAX_DIAGRAMS_PER_FILE=100
MAX_TOTAL_DIAGRAMS=1000

# Extended timeouts
VALIDATION_TIMEOUT=120000  # 2 minutes
CLEANUP_INTERVAL=1800000   # 30 minutes

# Comprehensive logging
LOG_LEVEL=info
LOG_TO_FILE=true
LOG_FORMAT=json
LOG_MAX_FILES=30
LOG_MAX_SIZE=100m

# Mermaid optimizations
MERMAID_MAX_TEXT_SIZE=200000
MERMAID_MAX_EDGES=2000
MERMAID_MAX_VERTICES=1000
```

## 🐳 Container Environment Variables

### Docker Environment
```dockerfile
# In Dockerfile
ENV NODE_ENV=production \
    PORT=8000 \
    HOST=0.0.0.0 \
    LOG_LEVEL=info \
    LOG_TO_FILE=true \
    TEMP_DIR=/app/tmp \
    VALIDATION_METHOD=pure \
    ENABLE_SVG_GENERATION=true
```

### Docker Compose Environment
```yaml
# In docker-compose.yml
environment:
  - NODE_ENV=production
  - PORT=8000
  - HOST=0.0.0.0
  - LOG_LEVEL=info
  - LOG_TO_FILE=true
  - MAX_FILE_SIZE=10485760
  - MAX_FILES=20
  - VALIDATION_TIMEOUT=30000
  - ENABLE_SVG_GENERATION=true
  - RATE_LIMIT_MAX_REQUESTS=100
  - CORS_ORIGIN=*
  - MEMORY_THRESHOLD=90
  - DISK_THRESHOLD=90
```

### Kubernetes Environment
```yaml
# In k8s deployment
env:
- name: NODE_ENV
  value: "production"
- name: PORT
  value: "8000"
- name: LOG_LEVEL
  value: "info"
- name: MAX_FILE_SIZE
  valueFrom:
    configMapKeyRef:
      name: mermaid-config
      key: max-file-size
- name: DATABASE_URL
  valueFrom:
    secretKeyRef:
      name: mermaid-secrets
      key: database-url
```

## 🔧 Development Environment Variables

### Local Development
```bash
NODE_ENV=development
PORT=8001
HOST=127.0.0.1
LOG_LEVEL=debug
LOG_TO_FILE=false
CORS_ORIGIN=*
RATE_LIMIT_MAX_REQUESTS=1000
VALIDATION_TIMEOUT=60000
ENABLE_SVG_GENERATION=true
VALIDATION_METHOD=pure
```

### Testing Environment
```bash
NODE_ENV=test
PORT=0  # Random available port
LOG_LEVEL=silent
LOG_TO_FILE=false
TEMP_DIR=./test-tmp
CLEANUP_INTERVAL=10000
MAX_FILES=5
MAX_TOTAL_DIAGRAMS=20
VALIDATION_TIMEOUT=5000
```

## 📋 Environment Variable Validation

The application validates all environment variables on startup:

- **Type checking**: Ensures integers are valid numbers
- **Range validation**: Checks values are within acceptable ranges
- **Required variables**: Fails fast if critical variables are missing
- **Default values**: Provides sensible defaults for optional variables

## 🚨 Security Considerations

### Sensitive Variables
Never expose these in logs or client-side code:
- Database credentials (if added)
- API keys (if added)
- JWT secrets (if added)
- File paths containing sensitive data

### Production Security
```bash
# Use restrictive CORS
CORS_ORIGIN="https://yourdomain.com"

# Lower rate limits
RATE_LIMIT_MAX_REQUESTS=50

# Disable debug logging
LOG_LEVEL=warn

# Enable all security features
HELMET_CSP_ENABLED=true
TRUST_PROXY=true

# Lower resource limits
MEMORY_THRESHOLD=80
DISK_THRESHOLD=80
```

## ✅ Environment Variable Checklist

Before deployment, verify:

- [ ] `NODE_ENV` is set to `production`
- [ ] `PORT` matches your deployment configuration
- [ ] `CORS_ORIGIN` is properly configured
- [ ] Rate limiting is appropriate for your use case
- [ ] File size limits match your requirements
- [ ] Logging configuration is suitable
- [ ] Health check thresholds are appropriate
- [ ] All paths (temp dir, log file) are writable
- [ ] No sensitive information is exposed