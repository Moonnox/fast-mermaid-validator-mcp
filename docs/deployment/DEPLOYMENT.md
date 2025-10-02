# Deployment Guide

## Overview

This document provides comprehensive deployment instructions for the Mermaid Validator API using both Kustomize and Helm on Kubernetes.

## Prerequisites

- Kubernetes cluster with kubectl configured
- Docker/Podman for container builds
- Helm 3.x (for Helm deployments)
- Access to a container registry (Docker Hub, GitHub Container Registry, etc.)

## Image Management

### Building Images

```bash
# Build AMD64 image for remote deployment
make deployment-amd64

# Build ARM64 image for local testing
make deployment-arm64
```

### Image Registry

Images can be stored in any container registry:
- Example Registry: Docker Hub, GitHub Container Registry (ghcr.io), etc.
- Repository: `mermaid-validator-mcp`
- Tags: `1.0.0-amd64`, `1.0.0-arm64`

### Pushing Images

```bash
# Push AMD64 image (requires registry authentication)
make push-amd64

# Push ARM64 image
make push-arm64
```

## Deployment Options

### 1. Kustomize Deployments

#### Development Environment (mmjc-dev)

```bash
# Deploy to mmjc-dev namespace
make k8s-deploy-dev

# Check status
make k8s-status-dev

# View logs
make k8s-logs-dev

# Delete deployment
make k8s-delete-dev
```

#### Test Environment (mmjc-test)

```bash
# Deploy to mmjc-test namespace
make k8s-deploy-test

# Check status
make k8s-status-test

# View logs
make k8s-logs-test

# Delete deployment
make k8s-delete-test
```

### 2. Helm Deployments

#### Development Environment

```bash
# Install Helm chart in mmjc-dev
make helm-install-dev

# Uninstall
make helm-uninstall-dev
```

#### Test Environment

```bash
# Install Helm chart in mmjc-test
make helm-install-test

# Uninstall
make helm-uninstall-test
```

### 3. Manual Deployments

#### Using kubectl directly

```bash
# Development
kubectl apply -k k8s/overlays/dev

# Test
kubectl apply -k k8s/overlays/test
```

#### Using Helm directly

```bash
# Development
helm upgrade --install mermaid-validator-mcp-dev helm/mermaid-validator-mcp \
  --namespace mmjc-dev \
  --values helm/mermaid-validator-mcp/values-dev.yaml \
  --create-namespace

# Test
helm upgrade --install mermaid-validator-mcp-test helm/mermaid-validator-mcp \
  --namespace mmjc-test \
  --values helm/mermaid-validator-mcp/values-test.yaml \
  --create-namespace
```

## Configuration

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| NODE_ENV | Application environment | production |
| PORT | Application port | 8000 |
| LOG_LEVEL | Logging level | info |

### Resource Allocation

#### Development Environment
- CPU: 250m requests, 500m limits
- Memory: 256Mi requests, 512Mi limits
- Replicas: 1

#### Test Environment
- CPU: 375m requests, 750m limits
- Memory: 384Mi requests, 768Mi limits
- Replicas: 2

#### Production Environment
- CPU: 500m requests, 1000m limits
- Memory: 512Mi requests, 1Gi limits
- Replicas: 2

## Networking

### Service Configuration

- Type: ClusterIP
- Port: 80 (external)
- Target Port: 8000 (internal)

### Ingress Configuration

- Class: nginx
- TLS: Enabled
- Hosts:
  - Development: `mermaid-validator-mcp-dev.mjc.internal`
  - Test: `mermaid-validator-mcp-test.mjc.internal`

### Network Policies

Network policies are enabled by default to restrict traffic:
- Allow ingress from any namespace on port 8000
- Allow all egress for external API access

## Health Checks

### Liveness Probe

- Path: `/api/v1/health`
- Initial Delay: 30s
- Period: 30s
- Timeout: 10s
- Failure Threshold: 3

### Readiness Probe

- Path: `/api/v1/health`
- Initial Delay: 10s
- Period: 5s
- Timeout: 5s
- Failure Threshold: 3

## Security Features

### Container Security

- Non-root user (UID: 1001)
- Read-only root filesystem capability
- All Linux capabilities dropped
- Security context enforced

### Network Security

- Network policies enabled
- TLS termination at ingress
- Internal service communication on port 8000

## Troubleshooting

### Common Issues

1. **ImagePullBackOff Error**
   ```bash
   # Check if image exists in registry
   docker pull icr.io/mjc-cr/mjc-mermaid-validator:1.0.0-amd64
   
   # Verify registry authentication
   docker login icr.io
   ```

2. **Pod Not Ready**
   ```bash
   # Check pod logs
   kubectl logs -n <namespace> -l app=mermaid-validator-mcp
   
   # Describe pod for events
   kubectl describe pod -n <namespace> <pod-name>
   ```

3. **Health Check Failures**
   ```bash
   # Test health endpoint directly
   kubectl port-forward -n <namespace> svc/mermaid-validator-mcp 8080:80
   curl http://localhost:8080/api/v1/health
   ```

### Monitoring

```bash
# Monitor deployment status
kubectl get deployments,pods,services,ingress -n <namespace> -l app=mermaid-validator-mcp

# Stream logs
kubectl logs -n <namespace> -l app=mermaid-validator-mcp -f

# Check resource usage
kubectl top pods -n <namespace> -l app=mermaid-validator-mcp
```

## Updating Deployments

### Rolling Updates

```bash
# Update image tag in kustomization.yaml or values.yaml
# Then apply changes
make k8s-deploy-dev  # for Kustomize
make helm-install-dev  # for Helm
```

### Rollback

```bash
# Helm rollback
helm rollback mermaid-validator-mcp-dev -n mmjc-dev

# Kubectl rollback
kubectl rollout undo deployment/mermaid-validator-mcp -n mmjc-dev
```

## Best Practices

1. **Always use specific image tags** (avoid `latest`)
2. **Test in dev before promoting to test**
3. **Monitor resource usage** and adjust limits as needed
4. **Use network policies** for security
5. **Enable TLS** for all external communication
6. **Implement proper logging** and monitoring
7. **Regular security scans** of container images