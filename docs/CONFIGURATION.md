# Mermaid Validator API - Configuration Guide

## Overview

The Mermaid Validator API is now **100% configurable** via environment variables with **unlimited mode support**. All previously hardcoded limits can now be dynamically configured per deployment environment.

## Configuration Architecture

### Environment Variable Pattern
```bash
# Standard limit
MAX_FILE_SIZE=104857600        # 100MB

# Unlimited mode
MAX_FILE_SIZE=-1               # Removes all limits

# Default fallback if not set
MAX_FILE_SIZE=                 # Uses hardcoded default
```

## Available Configuration Options

### File Upload Limits
```bash
MAX_FILE_SIZE=104857600        # 100MB (was 10MB)
MAX_FILES=100000               # 100k files (was 20)
MAX_REQUEST_SIZE=0             # Unlimited request body size
```

### Content Validation Limits
```bash
MAX_DIAGRAM_CONTENT_LENGTH=1048576    # 1MB (was 50k chars)
MAX_DIAGRAMS_PER_FILE=50              # 50 diagrams per file
MAX_TOTAL_DIAGRAMS=200                # 200 total diagrams per request
MAX_TIMEOUT_MS=60000                  # 60 seconds max processing
MAX_FILENAME_LENGTH=255               # 255 character filenames
```

### Mermaid Engine Limits
```bash
MERMAID_MAX_TEXT_SIZE=1048576         # 1MB (was 50k chars)
MERMAID_MAX_EDGES=500                 # 500 edges max
MERMAID_MAX_VERTICES=200              # 200 vertices max
```

### Processing Configuration
```bash
VALIDATION_TIMEOUT=30000              # 30 seconds validation timeout
PUPPETEER_TIMEOUT=10000               # 10 seconds puppeteer timeout
```

### Security Configuration
```bash
HSTS_MAX_AGE=31536000                 # 1 year HSTS
CORS_OPTIONS_SUCCESS_STATUS=200       # CORS success status
```

### Logging Configuration
```bash
LOG_LEVEL=info                        # info, debug, warn, error
LOG_TO_FILE=false                     # Enable file logging
LOG_FILE=logs/app.log                 # Log file path
LOG_MAX_FILES=5                       # Max log files to retain
LOG_MAX_SIZE=10m                      # Max size per log file
```

### Health Check Configuration
```bash
MEMORY_THRESHOLD=90                   # Memory usage threshold %
DISK_THRESHOLD=90                     # Disk usage threshold %
```

## Rate Limiting - API Gateway Delegated

**Rate limiting has been removed from the application** and delegated to API Gateway for:
- **Billing control** per customer plan
- **Consistent throttling** across all services
- **Plan-based limits** (Basic, Pro, Enterprise)

```bash
# These are NO LONGER USED (removed in v1.0.15)
# RATE_LIMIT_WINDOW_MS=900000
# RATE_LIMIT_MAX_REQUESTS=100
```

## Deployment Configurations

### Development Environment (mmjc-dev)
```yaml
env:
  NODE_ENV: "development"
  LOG_LEVEL: "debug"
  # High limits for development
  MAX_FILE_SIZE: "104857600"           # 100MB
  MAX_FILES: "100000"                  # 100k files
  MAX_DIAGRAM_CONTENT_LENGTH: "1048576" # 1MB
  MERMAID_MAX_TEXT_SIZE: "1048576"     # 1MB
  # Or set to -1 for unlimited
```

### Test Environment (mmjc-test)
```yaml
env:
  NODE_ENV: "production"
  LOG_LEVEL: "info"
  # Production-like limits
  MAX_FILE_SIZE: "104857600"           # 100MB
  MAX_FILES: "100000"                  # 100k files
  MAX_DIAGRAM_CONTENT_LENGTH: "1048576" # 1MB
  MERMAID_MAX_TEXT_SIZE: "1048576"     # 1MB
```

### Production Environment
```yaml
env:
  NODE_ENV: "production"
  LOG_LEVEL: "warn"
  # Conservative limits for stability
  MAX_FILE_SIZE: "52428800"            # 50MB
  MAX_FILES: "50000"                   # 50k files
  MAX_DIAGRAM_CONTENT_LENGTH: "524288" # 512KB
  MERMAID_MAX_TEXT_SIZE: "524288"      # 512KB
```

## Kubernetes Configuration Methods

### 1. ConfigMap (Kustomize)
```yaml
# k8s/base/configmap.yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: mermaid-validator-api-config
data:
  MAX_FILE_SIZE: "104857600"
  MAX_FILES: "100000"
  # Update via kubectl patch:
  # kubectl patch configmap mermaid-validator-api-config \
  #   --patch '{"data":{"MAX_FILE_SIZE":"104857600"}}'
```

### 2. Helm Values
```yaml
# helm/values.yaml
env:
  MAX_FILE_SIZE: "104857600"
  MAX_FILES: "100000"
  # Update via helm upgrade:
  # helm upgrade app . --set env.MAX_FILE_SIZE=104857600
```

### 3. Runtime Updates
```bash
# Live configuration updates (requires pod restart)
kubectl patch configmap mermaid-validator-api-config -n mmjc-dev --patch '{
  "data": {
    "MAX_FILE_SIZE": "104857600",
    "MAX_FILES": "100000",
    "MAX_DIAGRAM_CONTENT_LENGTH": "1048576",
    "MERMAID_MAX_TEXT_SIZE": "1048576"
  }
}'

# Restart deployment to pick up changes
kubectl rollout restart deployment/mermaid-validator-api -n mmjc-dev
```

## Horizontal Pod Autoscaler (HPA) Configuration

### Performance Metrics
- **CPU Target**: 70% utilization
- **Memory Target**: 70% utilization
- **Concurrent Requests**: ~200 per pod (tested)
- **Pod Range**: 2-20 replicas

### HPA Setup
```yaml
# hpa.yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: mermaid-validator-api-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: mermaid-validator-api
  minReplicas: 2
  maxReplicas: 20
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
  - type: Resource
    resource:
      name: memory
      target:
        type: Utilization
        averageUtilization: 70
  behavior:
    scaleDown:
      stabilizationWindowSeconds: 300
      policies:
      - type: Percent
        value: 10
        periodSeconds: 60
    scaleUp:
      stabilizationWindowSeconds: 0
      policies:
      - type: Percent
        value: 100
        periodSeconds: 15
```

## Migration History

### v1.0.13 → v1.0.14 (Configurable Limits)
- Added environment variable support for all limits
- Added unlimited mode (`-1` values)
- Maintained backward compatibility

### v1.0.14 → v1.0.15 (API Gateway Rate Limiting)
- Removed application-level rate limiting
- Delegated to API Gateway for billing control
- Simplified security middleware

## Monitoring & Observability

### Health Check Endpoint
```bash
curl http://localhost:8000/api/v1/health
```

### Configuration Verification
```bash
# Check active configuration
kubectl describe configmap mermaid-validator-api-config -n mmjc-dev

# View environment variables in pod
kubectl exec -it deployment/mermaid-validator-api -n mmjc-dev -- env | grep MAX_
```

### Load Testing
```bash
# Test concurrent request handling
for i in {1..200}; do
  curl -s -X POST http://localhost:8000/api/v1/validate \
    -H "Content-Type: application/json" \
    -d '{"diagrams":[{"content":"graph TD\nA-->B","type":"flowchart"}]}' &
done
wait
```

## Best Practices

1. **Environment-Specific Limits**: Use different limits per environment
2. **Gradual Increases**: Test limits before applying to production
3. **Monitor Resources**: Watch CPU/memory usage when increasing limits
4. **HPA Configuration**: Use autoscaling for variable workloads
5. **Health Monitoring**: Monitor endpoint response times
6. **Configuration Validation**: Test configuration changes in dev first

## Troubleshooting

### Common Issues
```bash
# Configuration not loading
kubectl describe pod <pod-name> | grep -A 10 "Environment:"

# High memory usage
kubectl top pods -n mmjc-dev

# Failed validations
kubectl logs deployment/mermaid-validator-api -n mmjc-dev --tail=100
```

### Performance Optimization
- **File Limits**: Balance between usability and resource usage
- **Concurrent Processing**: Monitor pod CPU/memory under load
- **Content Limits**: Large diagrams may require more processing time
- **HPA Tuning**: Adjust thresholds based on actual workload patterns