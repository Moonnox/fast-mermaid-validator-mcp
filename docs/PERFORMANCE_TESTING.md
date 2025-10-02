# Mermaid Validator API - Performance Testing & Load Limits

## Apache Bench Load Testing Results

### Test Environment
- **Tool**: Apache Bench (ab) Version 2.3
- **Deployment**: mmjc-dev namespace (single pod)
- **Test data**: Simple flowchart diagram (453 bytes response)
- **Configuration**: v1.0.15 with increased limits

### Performance Degradation Analysis

#### Baseline Performance (10 concurrent users)
```bash
ab -n 100 -c 10 -T application/json -p test_data.json http://localhost:8086/api/v1/validate
```
- **Requests per second**: 5.83 req/sec
- **Response time**: 1714ms mean, 171ms per concurrent request
- **Failed requests**: 6/100 (6% failure rate)
- **Status**: ✅ Stable performance

#### Moderate Load (20 concurrent users)
```bash
ab -n 200 -c 20 -T application/json -p test_data.json http://localhost:8086/api/v1/validate
```
- **Requests per second**: 11.65 req/sec (2x improvement)
- **Response time**: 1716ms mean, 86ms per concurrent request
- **Failed requests**: 6/200 (3% failure rate)
- **Status**: ✅ Good performance scaling

#### High Load (50 concurrent users)
```bash
ab -n 500 -c 50 -T application/json -p test_data.json http://localhost:8086/api/v1/validate
```
- **Requests per second**: 27.10 req/sec (4.6x improvement)
- **Response time**: 1845ms mean, 37ms per concurrent request
- **Failed requests**: 31/500 (6.2% failure rate)
- **Status**: ⚠️ Starting to show stress

#### **Degradation Point (100 concurrent users)**
```bash
ab -n 1000 -c 100 -T application/json -p test_data.json http://localhost:8086/api/v1/validate
```
- **Requests per second**: 48.75 req/sec (8.3x improvement)
- **Response time**: 2051ms mean, 20ms per concurrent request
- **Failed requests**: 72/1000 (7.2% failure rate)
- **Max response time**: 3171ms (significant degradation)
- **Status**: 🔴 **DEGRADATION THRESHOLD REACHED**

## Performance Characteristics

### Single Pod Limits
- **Optimal concurrency**: 20-50 concurrent requests
- **Degradation starts**: ~50 concurrent requests
- **Hard limit**: ~100 concurrent requests (7% failure rate)
- **Processing capacity**: ~50 req/sec max throughput

### Response Time Analysis
| Concurrency | Mean Response Time | 95th Percentile | Failure Rate |
|-------------|-------------------|-----------------|--------------|
| 10          | 1714ms            | 1793ms          | 6%           |
| 20          | 1716ms            | 1812ms          | 3%           |
| 50          | 1845ms            | 2050ms          | 6.2%         |
| **100**     | **2051ms**        | **2749ms**      | **7.2%**     |

## HPA Configuration for Fast Scaling

### Updated HPA Settings
```yaml
# Fast stabilization for both environments
behavior:
  scaleDown:
    stabilizationWindowSeconds: 60    # 1 minute (was 5 minutes)
    policies:
    - type: Percent
      value: 50                       # 50% reduction per cycle (was 10%)
      periodSeconds: 15               # Every 15 seconds
  scaleUp:
    stabilizationWindowSeconds: 0     # Immediate scaling
    policies:
    - type: Percent
      value: 200                      # Triple pods if needed (dev)
      periodSeconds: 10               # Every 10 seconds
    - type: Pods
      value: 8                        # Add max 8 pods at once (dev)
      periodSeconds: 10
```

### Environment-Specific Scaling

#### mmjc-dev (Development)
- **Min/Max replicas**: 2-20 pods
- **Scale-up**: 200% increase, +8 pods max per 10s
- **Scale-down**: 50% reduction per 15s after 1min stabilization
- **Target capacity**: 20 × 50 = **1,000 req/sec**

#### mmjc-test (Testing)
- **Min/Max replicas**: 2-15 pods
- **Scale-up**: 150% increase, +5 pods max per 10s
- **Scale-down**: 50% reduction per 15s after 1min stabilization
- **Target capacity**: 15 × 50 = **750 req/sec**

## Environment Variable Configuration

### Current Production Limits
```bash
# File handling
MAX_FILES=100000                     # 100k files
MAX_FILE_SIZE=104857600              # 100MB per file
MAX_REQUEST_SIZE=0                   # Unlimited request body

# Content processing
MAX_DIAGRAM_CONTENT_LENGTH=1048576   # 1MB per diagram
MAX_DIAGRAMS_PER_FILE=50             # 50 diagrams per file
MAX_TOTAL_DIAGRAMS=200               # 200 total diagrams per request

# Mermaid engine
MERMAID_MAX_TEXT_SIZE=1048576        # 1MB text processing
MERMAID_MAX_EDGES=500                # 500 edges max
MERMAID_MAX_VERTICES=200             # 200 vertices max

# Processing timeouts
VALIDATION_TIMEOUT=30000             # 30 seconds
MAX_TIMEOUT_MS=60000                 # 60 seconds max
```

### Unlimited Development Mode
```bash
# Set any limit to -1 for unlimited processing
MAX_FILES=-1                         # Unlimited files
MAX_FILE_SIZE=-1                     # Unlimited file size
MAX_DIAGRAM_CONTENT_LENGTH=-1        # Unlimited content
MERMAID_MAX_TEXT_SIZE=-1             # Unlimited mermaid processing
```

## Monitoring & Alerts

### Key Metrics to Watch
- **Response time > 2000ms**: Scale up trigger
- **Failure rate > 5%**: Performance degradation
- **CPU utilization > 70%**: HPA scale trigger
- **Memory utilization > 70%**: HPA scale trigger

### Load Testing Commands
```bash
# Quick performance check
ab -n 100 -c 10 -T application/json -p test_data.json http://api-endpoint/validate

# Stress test (degradation point)
ab -n 1000 -c 100 -T application/json -p test_data.json http://api-endpoint/validate

# Sustained load test
ab -n 5000 -c 50 -T application/json -p test_data.json http://api-endpoint/validate
```

## Recommendations

### Production Deployment
1. **Minimum 2 pods** for availability
2. **Target 50 concurrent requests per pod** for optimal performance
3. **Monitor failure rates** and scale before 100 concurrent/pod
4. **Use fast HPA scaling** (10s intervals) for traffic spikes

### Development Testing
1. **Test with realistic diagram sizes** (1KB+ content)
2. **Validate under sustained load** (5+ minute tests)
3. **Monitor memory usage** during large file uploads
4. **Test unlimited configuration** impacts

### Performance Optimization
1. **File upload limits** balance usability vs resources
2. **Content size limits** prevent memory exhaustion
3. **Concurrent processing** should not exceed 50/pod sustained
4. **HPA thresholds** should trigger before degradation point