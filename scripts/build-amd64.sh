#!/bin/bash

# AMD64 Build Script with Architecture Verification
# Author: Gregorio Elias Roecker Momm

set -e

COLORS_RED='\033[0;31m'
COLORS_GREEN='\033[0;32m'
COLORS_YELLOW='\033[1;33m'
COLORS_BLUE='\033[0;34m'
COLORS_NC='\033[0m'

log() {
    echo -e "${2:-$COLORS_NC}$1${COLORS_NC}"
}

# Get current version
VERSION=$(grep '"version"' package.json | cut -d '"' -f 4)
REGISTRY="icr.io/mjc-cr"
IMAGE_NAME="mjc-mermaid-validator"
FULL_IMAGE_NAME="$REGISTRY/$IMAGE_NAME"

log "Building AMD64 Docker Image with Verification" $COLORS_BLUE
log "=============================================" $COLORS_BLUE
log "Version: $VERSION" $COLORS_BLUE

# Check for buildx
if command -v docker-buildx >/dev/null 2>&1 || docker buildx version >/dev/null 2>&1; then
    log "✓ Docker buildx available" $COLORS_GREEN
    
    # Create multi-arch builder if needed
    if ! docker buildx inspect multi-arch-builder >/dev/null 2>&1; then
        log "Creating multi-architecture builder..." $COLORS_YELLOW
        docker buildx create --name multi-arch-builder --driver docker-container --use
        docker buildx inspect --bootstrap
    else
        log "Using existing multi-arch builder" $COLORS_GREEN
        docker buildx use multi-arch-builder
    fi
    
    # Build AMD64 with buildx
    log "Building AMD64 image with buildx..." $COLORS_BLUE
    docker buildx build \
        --platform linux/amd64 \
        --tag "$FULL_IMAGE_NAME:$VERSION-amd64" \
        --tag "$FULL_IMAGE_NAME:latest-amd64" \
        --push \
        .
    
    log "✓ AMD64 image built and pushed with buildx" $COLORS_GREEN
    
    # Pull and verify
    docker pull "$FULL_IMAGE_NAME:$VERSION-amd64"
    ARCH=$(docker inspect "$FULL_IMAGE_NAME:$VERSION-amd64" | jq -r '.[0].Architecture')
    
else
    log "⚠ Docker buildx not available" $COLORS_YELLOW
    log "Attempting standard build (will likely be wrong architecture)" $COLORS_YELLOW
    
    # Standard build
    docker build --tag "$FULL_IMAGE_NAME:$VERSION-amd64" .
    docker tag "$FULL_IMAGE_NAME:$VERSION-amd64" "$FULL_IMAGE_NAME:latest-amd64"
    
    ARCH=$(docker inspect "$FULL_IMAGE_NAME:$VERSION-amd64" | jq -r '.[0].Architecture')
fi

# Verify architecture
log "\n=== ARCHITECTURE VERIFICATION ===" $COLORS_BLUE
log "Expected: amd64" $COLORS_BLUE
log "Actual: $ARCH" $COLORS_BLUE

if [ "$ARCH" = "amd64" ]; then
    log "✓ Architecture verification PASSED" $COLORS_GREEN
    
    # Push if not already pushed
    if ! command -v docker-buildx >/dev/null 2>&1 && ! docker buildx version >/dev/null 2>&1; then
        log "Pushing images to registry..." $COLORS_BLUE
        docker push "$FULL_IMAGE_NAME:$VERSION-amd64"
        docker push "$FULL_IMAGE_NAME:latest-amd64"
        log "✓ Images pushed to registry" $COLORS_GREEN
    fi
    
    # Test the image
    log "\n=== TESTING BUILT IMAGE ===" $COLORS_BLUE
    log "Starting container to verify functionality..." $COLORS_YELLOW
    
    CONTAINER_ID=$(docker run -d --rm -p 8002:8000 "$FULL_IMAGE_NAME:$VERSION-amd64")
    sleep 10
    
    if curl -s http://localhost:8002/api/v1/health >/dev/null; then
        log "✓ Container health check PASSED" $COLORS_GREEN
        PARSERS=$(curl -s http://localhost:8002/api/v1/health | jq -r '.checks.parsers.availableTypes | length' 2>/dev/null || echo "unknown")
        log "✓ Parsers loaded: $PARSERS" $COLORS_GREEN
    else
        log "✗ Container health check FAILED" $COLORS_RED
    fi
    
    docker stop $CONTAINER_ID >/dev/null 2>&1 || true
    
else
    log "✗ Architecture verification FAILED" $COLORS_RED
    log "Expected amd64 but got $ARCH" $COLORS_RED
    log "\nTo fix this issue:" $COLORS_YELLOW
    log "1. Install Docker buildx" $COLORS_YELLOW
    log "2. Or build on AMD64 environment" $COLORS_YELLOW
    log "3. Or use GitHub Actions for cross-platform builds" $COLORS_YELLOW
    exit 1
fi

log "\n=== BUILD SUMMARY ===" $COLORS_BLUE
log "✓ Version: $VERSION" $COLORS_GREEN
log "✓ Architecture: $ARCH" $COLORS_GREEN
log "✓ Registry: $FULL_IMAGE_NAME:$VERSION-amd64" $COLORS_GREEN
log "✓ Latest: $FULL_IMAGE_NAME:latest-amd64" $COLORS_GREEN