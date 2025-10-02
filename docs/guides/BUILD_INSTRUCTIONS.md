# Production Build Instructions

## Current Status (Updated)
- **Latest Working Image:** v1.0.14-amd64 ✅
- **Production Deployment:** Successfully deployed with MIME type improvements
- **Architecture:** Single-stage Alpine Linux build optimized for AMD64
- **Build System:** Local Docker build with registry push

## Solution: Docker Buildx Setup

### 1. Install/Enable Docker Buildx
```bash
# Check if buildx is available
docker buildx version

# If not available, install buildx plugin
mkdir -p ~/.docker/cli-plugins
curl -L https://github.com/docker/buildx/releases/latest/download/buildx-darwin-arm64 -o ~/.docker/cli-plugins/docker-buildx
chmod +x ~/.docker/cli-plugins/docker-buildx
```

### 2. Create Multi-Platform Builder
```bash
# Create builder instance
docker buildx create --name multi-arch --driver docker-container --use

# Bootstrap the builder
docker buildx inspect --bootstrap
```

### 3. Updated Makefile Target
```makefile
docker-build-amd64: version-patch ## Build AMD64 with buildx verification
	@BUILD_VERSION=$$(grep '"version"' package.json | cut -d '"' -f 4) && \
	echo "Building $$BUILD_VERSION for AMD64 using buildx..." && \
	docker buildx build \
		--platform linux/amd64 \
		--tag $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 \
		--tag $(FULL_IMAGE_NAME):latest-amd64 \
		--push \
		. && \
	echo "Verifying pushed image..." && \
	docker pull $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 && \
	ARCH=$$(docker inspect $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 | jq -r '.[0].Architecture') && \
	echo "Architecture: $$ARCH" && \
	if [ "$$ARCH" = "amd64" ]; then \
		echo "✓ AMD64 verification passed"; \
	else \
		echo "✗ Architecture verification failed"; \
	fi
```

### 4. Multi-Platform Build (Both AMD64 + ARM64)
```bash
docker buildx build \
  --platform linux/amd64,linux/arm64 \
  --tag icr.io/mjc-cr/mjc-mermaid-validator:1.0.8-multi \
  --push \
  .
```

## Production Build Process

### Current Workflow (v1.0.14)
```bash
# 1. Update version in Dockerfile
# 2. Build and push image
docker build --load -t icr.io/mjc-cr/mjc-mermaid-validator:1.0.14-amd64 .
docker push icr.io/mjc-cr/mjc-mermaid-validator:1.0.14-amd64

# 3. Update Kubernetes deployment
kubectl set image deployment/mermaid-validator-mcp mermaid-validator-mcp=icr.io/mjc-cr/mjc-mermaid-validator:1.0.14-amd64 -n mmjc-dev
kubectl rollout restart deployment/mermaid-validator-mcp -n mmjc-dev
```

### Key Improvements in v1.0.14
- **MIME Type Flexibility**: Supports file uploads with or without explicit MIME types
- **File Extension Validation**: Dual validation using both MIME type and file extension
- **Optimized Dockerfile**: Single-stage Alpine build with proper permission handling
- **Security Enhancements**: Enhanced file upload security and validation

## Architecture Verification Command
```bash
docker inspect IMAGE_NAME | jq '.[0].Architecture'
```

Expected result for Kubernetes: `"amd64"`