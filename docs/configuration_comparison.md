# Mermaid Validator API - Configuration Comparison

## Previous Configuration (Hardcoded Limits)

### Rate Limiting
```javascript
// src/config/config.js (BEFORE)
rateLimit: {
  windowMs: 15 * 60 * 1000, // 15 minutes (HARDCODED)
  max: 100, // limit each IP to 100 requests per windowMs (HARDCODED)
}
```

### Validation Limits
```javascript
// src/middleware/security.js (BEFORE)
body('diagrams')
  .isArray({ min: 1, max: config.validation.maxTotalDiagrams }) // 200 max diagrams
body('diagrams.*.content')
  .isLength({ min: 1, max: 50000 }) // 50,000 characters MAX (HARDCODED)
body('options.timeout')
  .isInt({ min: 1000, max: 60000 }) // 60 seconds MAX (HARDCODED)

// File validation
if (fileName.length > 255) // 255 chars MAX (HARDCODED)

// Mermaid limits
maxTextSize: 50000,  // HARDCODED
maxEdges: 500,       // HARDCODED
maxVertices: 200     // HARDCODED
```

### File Upload Limits
```javascript
// src/config/config.js (BEFORE)
upload: {
  maxFileSize: 10 * 1024 * 1024, // 10MB (HARDCODED)
  maxFiles: 20, // HARDCODED
}
```

## Current Configuration (100% Environment Variables)

### All Limits Now Configurable
```bash
# Rate Limiting
RATE_LIMIT_WINDOW_MS=900000          # 15 minutes (was hardcoded)
RATE_LIMIT_MAX_REQUESTS=100          # 100 requests (was hardcoded)

# Content Validation
MAX_DIAGRAM_CONTENT_LENGTH=50000     # 50k chars (was hardcoded)
MAX_TIMEOUT_MS=60000                 # 60 seconds (was hardcoded)
MAX_FILENAME_LENGTH=255              # 255 chars (was hardcoded)

# File Upload
MAX_FILE_SIZE=10485760               # 10MB (was hardcoded)
MAX_FILES=20                         # 20 files (was hardcoded)

# Diagram Limits
MAX_DIAGRAMS_PER_FILE=50             # Already configurable
MAX_TOTAL_DIAGRAMS=200               # Already configurable

# Mermaid Engine Limits
MERMAID_MAX_TEXT_SIZE=50000          # 50k chars (was hardcoded)
MERMAID_MAX_EDGES=500                # 500 edges (was hardcoded)
MERMAID_MAX_VERTICES=200             # 200 vertices (was hardcoded)
```

### Unlimited Configuration Support
```bash
# Set ANY limit to -1 or 0 for UNLIMITED
RATE_LIMIT_MAX_REQUESTS=-1           # Unlimited requests
MAX_DIAGRAM_CONTENT_LENGTH=-1        # Unlimited content size
MAX_FILE_SIZE=-1                     # Unlimited file size
MAX_FILES=-1                         # Unlimited file count
MERMAID_MAX_TEXT_SIZE=-1             # Unlimited mermaid size
# etc...
```

## Current Deployments

### mmjc-dev (Unlimited Configuration)
```yaml
env:
  NODE_ENV: "development"
  LOG_LEVEL: "debug"
  RATE_LIMIT_MAX_REQUESTS: "-1"      # UNLIMITED
  MAX_DIAGRAM_CONTENT_LENGTH: "-1"   # UNLIMITED
  MAX_FILE_SIZE: "-1"                # UNLIMITED
  # All limits set to -1 (unlimited)
```

### mmjc-test (Standard Configuration)
```yaml
env:
  NODE_ENV: "production"
  LOG_LEVEL: "info"
  RATE_LIMIT_MAX_REQUESTS: "100"     # Standard limit
  MAX_DIAGRAM_CONTENT_LENGTH: "50000" # Standard limit
  MAX_FILE_SIZE: "10485760"          # Standard limit
  # All limits use production values
```

## Key Improvements

1. **🔧 100% Configurable**: All previous hardcoded limits now use environment variables
2. **♾️ Unlimited Support**: Set any limit to `-1` to disable completely
3. **🚀 Environment-Specific**: Different limits per deployment (dev vs test vs prod)
4. **🔄 Runtime Configurable**: Change limits without code changes
5. **📊 Monitoring Ready**: Centralized configuration management

## Before/After Summary

| Aspect | Before | After |
|--------|--------|-------|
| **Rate Limiting** | Hardcoded 100 req/15min | Configurable via `RATE_LIMIT_MAX_REQUESTS` |
| **Content Size** | Hardcoded 50k chars | Configurable via `MAX_DIAGRAM_CONTENT_LENGTH` |
| **File Size** | Hardcoded 10MB | Configurable via `MAX_FILE_SIZE` |
| **Mermaid Limits** | Hardcoded 500/200 | Configurable via `MERMAID_MAX_*` |
| **Unlimited Mode** | Not possible | Set any limit to `-1` |
| **Configuration** | Code changes required | Environment variables only |