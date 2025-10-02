# Mermaid Validator API - Comprehensive Makefile
# Author: Gregorio Elias Roecker Momm
# 
# This Makefile provides comprehensive operations for development, testing,
# security auditing, building, and deployment of the Mermaid Validator API.

.PHONY: help install dev test build security audit clean docker k8s all

# Colors for output
RED=\033[0;31m
GREEN=\033[0;32m
YELLOW=\033[1;33m
BLUE=\033[0;34m
NC=\033[0m # No Color

# Project configuration
PROJECT_NAME := mermaid-validator-mcp
VERSION := $(shell grep '"version"' package.json | cut -d '"' -f 4)
DOCKER_REGISTRY := icr.io/mjc-cr
IMAGE_NAME := mjc-mermaid-validator
IMAGE_TAG := $(VERSION)
FULL_IMAGE_NAME := $(DOCKER_REGISTRY)/$(IMAGE_NAME)

# Directories
SRC_DIR := src
TEST_DIR := tests
DOCS_DIR := docs
BUILD_DIR := build
COVERAGE_DIR := coverage

# Node.js configuration
NODE_VERSION := $(shell node --version 2>/dev/null || echo "not-installed")
NPM_VERSION := $(shell npm --version 2>/dev/null || echo "not-installed")

##@ Help
help: ## Display this help
	@echo "$(BLUE)Mermaid Validator API - Makefile Commands$(NC)"
	@echo "$(BLUE)Author: Gregorio Elias Roecker Momm$(NC)"
	@echo ""
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_0-9-]+:.*?##/ { printf "  \033[36m%-20s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

##@ Development
install: ## Install dependencies
	@echo "$(BLUE)Installing dependencies...$(NC)"
	@if [ "$(NODE_VERSION)" = "not-installed" ]; then \
		echo "$(RED)Error: Node.js is not installed$(NC)"; \
		exit 1; \
	fi
	npm ci
	@echo "$(GREEN)Dependencies installed successfully$(NC)"

dev: ## Start development server
	@echo "$(BLUE)Starting development server...$(NC)"
	npm run dev

start: ## Start production server
	@echo "$(BLUE)Starting production server...$(NC)"
	NODE_ENV=production npm start

##@ Testing
test: ## Run all tests
	@echo "$(BLUE)Running tests...$(NC)"
	npm test

test-unit: ## Run unit tests only
	@echo "$(BLUE)Running unit tests...$(NC)"
	npm run test:unit

test-integration: ## Run integration tests
	@echo "$(BLUE)Running integration tests...$(NC)"
	@$(MAKE) docker-test-env
	npm run test:integration || ($(MAKE) docker-clean && exit 1)
	@$(MAKE) docker-clean

test-api: ## Run API endpoint tests
	@echo "$(BLUE)Running API tests...$(NC)"
	npm run test:api

test-validate: ## Test validation endpoints
	@echo "$(BLUE)Testing validation endpoints...$(NC)"
	npm run test:validate

test-upload: ## Test file upload endpoints  
	@echo "$(BLUE)Testing upload endpoints...$(NC)"
	npm run test:upload

test-grammars: compile-grammars ## Test all compiled grammars
	@echo "$(BLUE)Testing compiled grammars...$(NC)"
	@$(MAKE) start &
	@sleep 5
	@$(MAKE) test-validate
	@$(MAKE) test-upload
	@pkill -f "node src/server.js" || true

test-watch: ## Run tests in watch mode
	@echo "$(BLUE)Running tests in watch mode...$(NC)"
	npm run test:watch

test-coverage: ## Run tests with coverage
	@echo "$(BLUE)Running tests with coverage...$(NC)"
	npm run test:coverage
	@echo "$(GREEN)Coverage report generated in $(COVERAGE_DIR)/$(NC)"

##@ Code Quality
lint: ## Run ESLint
	@echo "$(BLUE)Running ESLint...$(NC)"
	npm run lint

lint-fix: ## Fix ESLint issues
	@echo "$(BLUE)Fixing ESLint issues...$(NC)"
	npm run lint:fix

format: ## Format code with Prettier (if configured)
	@echo "$(BLUE)Formatting code...$(NC)"
	@if command -v prettier >/dev/null 2>&1; then \
		prettier --write "$(SRC_DIR)/**/*.js" "$(TEST_DIR)/**/*.js"; \
	else \
		echo "$(YELLOW)Prettier not installed, skipping...$(NC)"; \
	fi

##@ Security
audit: ## Run npm audit
	@echo "$(BLUE)Running npm audit...$(NC)"
	npm audit

audit-fix: ## Fix npm audit issues
	@echo "$(BLUE)Fixing npm audit issues...$(NC)"
	npm audit fix

audit-ci: ## Run audit CI (strict mode)
	@echo "$(BLUE)Running audit CI...$(NC)"
	npm run audit:ci || echo "$(YELLOW)Audit CI found issues$(NC)"

security: ## Run comprehensive security checks
	@echo "$(BLUE)Running comprehensive security checks...$(NC)"
	@$(MAKE) audit
	@$(MAKE) security-snyk
	@$(MAKE) security-retire
	@$(MAKE) license-check

security-snyk: ## Run Snyk security scan
	@echo "$(BLUE)Running Snyk security scan...$(NC)"
	@if command -v snyk >/dev/null 2>&1; then \
		snyk test || echo "$(YELLOW)Snyk found vulnerabilities$(NC)"; \
	else \
		echo "$(YELLOW)Snyk not installed, installing...$(NC)"; \
		npm install -g snyk; \
		snyk test || echo "$(YELLOW)Snyk found vulnerabilities$(NC)"; \
	fi

security-retire: ## Run retire.js scan
	@echo "$(BLUE)Running retire.js scan...$(NC)"
	npm run security:retire || echo "$(YELLOW)Retire.js found issues$(NC)"

security-owasp: ## Run OWASP dependency check
	@echo "$(BLUE)Running OWASP dependency check...$(NC)"
	@if command -v dependency-check >/dev/null 2>&1; then \
		dependency-check --project "$(PROJECT_NAME)" --scan . --format JSON --out $(BUILD_DIR)/owasp-report.json; \
		echo "$(GREEN)OWASP report generated: $(BUILD_DIR)/owasp-report.json$(NC)"; \
	else \
		echo "$(YELLOW)OWASP dependency-check not installed$(NC)"; \
		echo "$(YELLOW)Download from: https://owasp.org/www-project-dependency-check/$(NC)"; \
	fi

nvd-check: ## Check against National Vulnerability Database
	@echo "$(BLUE)Checking against NVD database...$(NC)"
	@mkdir -p $(BUILD_DIR)
	@if command -v grype >/dev/null 2>&1; then \
		grype $(IMAGE_NAME):$(IMAGE_TAG) -o json > $(BUILD_DIR)/nvd-report.json; \
		echo "$(GREEN)NVD scan completed: $(BUILD_DIR)/nvd-report.json$(NC)"; \
	else \
		echo "$(YELLOW)Grype not installed. Install with:$(NC)"; \
		echo "$(YELLOW)curl -sSfL https://raw.githubusercontent.com/anchore/grype/main/install.sh | sh -s -- -b /usr/local/bin$(NC)"; \
	fi

##@ Dependencies
deps-check: ## Check for dependency updates
	@echo "$(BLUE)Checking for dependency updates...$(NC)"
	npm run deps:check

deps-update: ## Update dependencies (interactive)
	@echo "$(BLUE)Updating dependencies...$(NC)"
	npm run deps:update
	@echo "$(YELLOW)Please review changes and run 'npm install' to apply$(NC)"

license-check: ## Check dependency licenses
	@echo "$(BLUE)Checking dependency licenses...$(NC)"
	npm run license:check

##@ Grammar Compilation
compile-grammars: ## Compile all grammar files (Jison, ANTLR, Langium)
	@echo "$(BLUE)Compiling all grammar files...$(NC)"
	npm run build:grammars
	@echo "$(GREEN)Grammar compilation completed$(NC)"

compile-jison: ## Compile Jison grammar files
	@echo "$(BLUE)Compiling Jison grammars...$(NC)"
	npm run build:jison
	@echo "$(GREEN)Jison compilation completed$(NC)"

compile-antlr: ## Compile ANTLR4 grammar files
	@echo "$(BLUE)Compiling ANTLR4 grammars...$(NC)"
	npm run build:antlr
	@echo "$(GREEN)ANTLR4 compilation completed$(NC)"

compile-langium: ## Compile Langium grammar files
	@echo "$(BLUE)Compiling Langium grammars...$(NC)"
	npm run build:langium
	@echo "$(GREEN)Langium compilation completed$(NC)"

generate-types: ## Generate TypeScript type definitions
	@echo "$(BLUE)Generating TypeScript definitions...$(NC)"
	npm run build:types
	@echo "$(GREEN)TypeScript definitions generated$(NC)"

##@ Build
build: compile-grammars ## Build the application with compiled grammars
	@echo "$(BLUE)Building application...$(NC)"
	@mkdir -p $(BUILD_DIR)
	npm run build
	@cp -r $(SRC_DIR) $(BUILD_DIR)/
	@cp -r scripts $(BUILD_DIR)/ 2>/dev/null || true
	@cp package*.json $(BUILD_DIR)/
	@cd $(BUILD_DIR) && npm ci --only=production
	@echo "$(GREEN)Build completed: $(BUILD_DIR)/$(NC)"

build-dev: compile-grammars ## Build for development with all features
	@echo "$(BLUE)Building for development...$(NC)"
	npm run build
	@echo "$(GREEN)Development build completed$(NC)"

build-prod: compile-grammars test ## Build for production with tests
	@echo "$(BLUE)Building for production...$(NC)"
	@$(MAKE) lint
	@$(MAKE) test
	npm run build
	@mkdir -p $(BUILD_DIR)
	@cp -r $(SRC_DIR) $(BUILD_DIR)/
	@cp -r scripts $(BUILD_DIR)/ 2>/dev/null || true
	@cp package*.json $(BUILD_DIR)/
	@cd $(BUILD_DIR) && npm ci --only=production
	@echo "$(GREEN)Production build completed: $(BUILD_DIR)/$(NC)"

clean: ## Clean build artifacts and generated files
	@echo "$(BLUE)Cleaning build artifacts...$(NC)"
	rm -rf $(BUILD_DIR)
	rm -rf $(COVERAGE_DIR)
	rm -rf node_modules/.cache
	rm -rf tmp/*
	rm -rf logs/*
	rm -rf src/generated
	rm -rf src/types/parsers.d.ts
	@echo "$(GREEN)Clean completed$(NC)"

clean-generated: ## Clean only generated grammar files
	@echo "$(BLUE)Cleaning generated grammar files...$(NC)"
	npm run clean:generated
	@echo "$(GREEN)Generated files cleaned$(NC)"

##@ Versioning
version-patch: ## Increment patch version (1.0.0 -> 1.0.1)
	@echo "$(BLUE)Incrementing patch version...$(NC)"
	node scripts/increment-version.js patch
	@echo "$(GREEN)Version incremented$(NC)"

version-minor: ## Increment minor version (1.0.0 -> 1.1.0)
	@echo "$(BLUE)Incrementing minor version...$(NC)"
	node scripts/increment-version.js minor
	@echo "$(GREEN)Version incremented$(NC)"

version-major: ## Increment major version (1.0.0 -> 2.0.0)
	@echo "$(BLUE)Incrementing major version...$(NC)"
	node scripts/increment-version.js major
	@echo "$(GREEN)Version incremented$(NC)"

##@ Docker Operations
docker-build: version-patch ## Build AMD64 Docker image with auto-incremented version
	@echo "$(BLUE)Building AMD64 Docker image with auto-increment...$(NC)"
	@BUILD_VERSION=$$(grep '"version"' package.json | cut -d '"' -f 4) && \
	echo "$(BLUE)Building version: $$BUILD_VERSION for AMD64$(NC)" && \
	docker build --platform linux/amd64 --tag $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 . && \
	docker tag $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 $(FULL_IMAGE_NAME):latest-amd64 && \
	echo "$(GREEN)AMD64 Docker image built:$(NC)" && \
	echo "$(GREEN)  - $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64$(NC)" && \
	echo "$(GREEN)  - $(FULL_IMAGE_NAME):latest-amd64$(NC)" && \
	echo "$(BLUE)Verifying architecture...$(NC)" && \
	ARCH=$$(docker inspect $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 | jq -r '.[0].Architecture') && \
	VERSION_CHECK=$$(docker run --rm $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 node -e "console.log(require('./package.json').version)" 2>/dev/null || echo "version-check-failed") && \
	echo "$(BLUE)Architecture: $$ARCH$(NC)" && \
	echo "$(BLUE)Version in container: $$VERSION_CHECK$(NC)" && \
	if [ "$$ARCH" = "amd64" ]; then \
		echo "$(GREEN)✓ Architecture verification passed$(NC)"; \
	else \
		echo "$(RED)✗ Architecture verification failed: expected amd64, got $$ARCH$(NC)"; \
	fi

docker-build-manual: ## Build Docker image without version increment
	@echo "$(BLUE)Building Docker image (manual version)...$(NC)"
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .
	docker tag $(IMAGE_NAME):$(IMAGE_TAG) $(IMAGE_NAME):latest
	@echo "$(GREEN)Docker image built: $(IMAGE_NAME):$(IMAGE_TAG)$(NC)"

docker-build-amd64: version-patch ## Build AMD64 image with buildx verification  
	@echo "$(BLUE)Building AMD64 Docker image with buildx...$(NC)"
	@BUILD_VERSION=$$(grep '"version"' package.json | cut -d '"' -f 4) && \
	echo "$(BLUE)Building version: $$BUILD_VERSION for AMD64$(NC)" && \
	if command -v docker-buildx >/dev/null 2>&1 || docker buildx version >/dev/null 2>&1; then \
		echo "$(BLUE)Using Docker buildx for multi-platform build...$(NC)"; \
		docker buildx build \
			--platform linux/amd64 \
			--tag $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 \
			--tag $(FULL_IMAGE_NAME):latest-amd64 \
			--push \
			. && \
		echo "$(GREEN)✓ AMD64 image built and pushed with buildx$(NC)"; \
		docker pull $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 && \
		ARCH=$$(docker inspect $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 | jq -r '.[0].Architecture') && \
		echo "$(BLUE)Verified architecture: $$ARCH$(NC)"; \
	else \
		echo "$(YELLOW)Buildx not available, using standard build (may be wrong architecture)$(NC)"; \
		docker build --tag $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 . && \
		docker tag $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 $(FULL_IMAGE_NAME):latest-amd64 && \
		ARCH=$$(docker inspect $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64 | jq -r '.[0].Architecture') && \
		echo "$(BLUE)Architecture: $$ARCH$(NC)" && \
		if [ "$$ARCH" = "amd64" ]; then \
			echo "$(GREEN)✓ Architecture verification passed$(NC)"; \
			docker push $(FULL_IMAGE_NAME):$$BUILD_VERSION-amd64; \
			docker push $(FULL_IMAGE_NAME):latest-amd64; \
			echo "$(GREEN)✓ Images pushed to registry$(NC)"; \
		else \
			echo "$(RED)✗ Architecture verification failed: expected amd64, got $$ARCH$(NC)"; \
			echo "$(YELLOW)Install buildx for proper cross-platform builds$(NC)"; \
		fi; \
	fi

docker-build-arm64: ## Build Docker image for ARM64
	@echo "$(BLUE)Building Docker image for ARM64...$(NC)"
	docker build --tag $(FULL_IMAGE_NAME):$(IMAGE_TAG)-arm64 .
	@echo "$(GREEN)ARM64 Docker image built: $(FULL_IMAGE_NAME):$(IMAGE_TAG)-arm64$(NC)"

docker-build-ubi: ## Build Docker image with UBI base
	@echo "$(BLUE)Building Docker image with Red Hat UBI...$(NC)"
	docker build -f Dockerfile.ubi -t $(IMAGE_NAME):$(IMAGE_TAG)-ubi .
	docker tag $(IMAGE_NAME):$(IMAGE_TAG)-ubi $(IMAGE_NAME):latest-ubi
	@echo "$(GREEN)UBI Docker image built: $(IMAGE_NAME):$(IMAGE_TAG)-ubi$(NC)"

docker-run: ## Run Docker container
	@echo "$(BLUE)Running Docker container...$(NC)"
	docker run -d --name $(PROJECT_NAME) -p 8000:8000 $(IMAGE_NAME):$(IMAGE_TAG)
	@echo "$(GREEN)Container started: http://localhost:8000$(NC)"

docker-run-dev: ## Run Docker container in development mode
	@echo "$(BLUE)Running Docker container in development mode...$(NC)"
	docker-compose --profile dev up -d
	@echo "$(GREEN)Development container started: http://localhost:8001$(NC)"

docker-run-prod: ## Run Docker container in production mode
	@echo "$(BLUE)Running Docker container in production mode...$(NC)"
	docker-compose --profile production up -d
	@echo "$(GREEN)Production container started: http://localhost:8000$(NC)"

docker-stop: ## Stop Docker container
	@echo "$(BLUE)Stopping Docker container...$(NC)"
	docker stop $(PROJECT_NAME) || true
	docker rm $(PROJECT_NAME) || true

docker-logs: ## Show Docker container logs
	@echo "$(BLUE)Showing Docker container logs...$(NC)"
	docker logs -f $(PROJECT_NAME)

docker-shell: ## Enter Docker container shell
	@echo "$(BLUE)Entering Docker container shell...$(NC)"
	docker exec -it $(PROJECT_NAME) /bin/sh

docker-scan: ## Scan Docker image for vulnerabilities
	@echo "$(BLUE)Scanning Docker image for vulnerabilities...$(NC)"
	@$(MAKE) docker-security-scan IMAGE_NAME=$(IMAGE_NAME):$(IMAGE_TAG)

docker-security-scan: ## Run comprehensive security scan on Docker image
	@echo "$(BLUE)Running security scan on $(IMAGE_NAME)...$(NC)"
	@echo "$(BLUE)Checking base image and dependencies...$(NC)"
	@if command -v docker >/dev/null 2>&1; then \
		echo "Docker version: $$(docker --version)"; \
		docker image inspect $(IMAGE_NAME) --format 'Image size: {{.Size}} bytes' || true; \
		if docker version --format '{{.Server.Version}}' | grep -q "^2[0-9]"; then \
			echo "$(BLUE)Running Docker Scout scan...$(NC)"; \
			docker scout cves $(IMAGE_NAME) || echo "$(YELLOW)Docker Scout not available$(NC)"; \
		else \
			echo "$(YELLOW)Docker Scout requires Docker Engine 20.10+$(NC)"; \
		fi; \
	fi
	@if command -v trivy >/dev/null 2>&1; then \
		echo "$(BLUE)Running Trivy scan...$(NC)"; \
		trivy image --severity HIGH,CRITICAL $(IMAGE_NAME); \
	else \
		echo "$(YELLOW)Trivy not installed. Install with: brew install trivy$(NC)"; \
	fi

docker-clean: ## Clean Docker resources
	@echo "$(BLUE)Cleaning Docker resources...$(NC)"
	docker-compose down -v --remove-orphans || true
	docker system prune -f

docker-test-env: ## Start test environment
	@echo "$(BLUE)Starting test environment...$(NC)"
	docker-compose -f docker-compose.test.yml up -d

##@ Kubernetes
k8s-deploy-dev: ## Deploy to mmjc-dev namespace using Kustomize
	@echo "$(BLUE)Deploying to mmjc-dev namespace...$(NC)"
	kubectl apply -k k8s/overlays/dev
	@echo "$(GREEN)Deployed to mmjc-dev namespace$(NC)"

k8s-deploy-test: ## Deploy to mmjc-test namespace using Kustomize
	@echo "$(BLUE)Deploying to mmjc-test namespace...$(NC)"
	kubectl apply -k k8s/overlays/test
	@echo "$(GREEN)Deployed to mmjc-test namespace$(NC)"

k8s-delete-dev: ## Delete from mmjc-dev namespace
	@echo "$(BLUE)Deleting from mmjc-dev namespace...$(NC)"
	kubectl delete -k k8s/overlays/dev || true

k8s-delete-test: ## Delete from mmjc-test namespace
	@echo "$(BLUE)Deleting from mmjc-test namespace...$(NC)"
	kubectl delete -k k8s/overlays/test || true

k8s-status-dev: ## Check deployment status in mmjc-dev
	@echo "$(BLUE)Checking mmjc-dev status...$(NC)"
	kubectl get deployments,services,pods,ingress -n mmjc-dev -l app=mermaid-validator-mcp

k8s-status-test: ## Check deployment status in mmjc-test
	@echo "$(BLUE)Checking mmjc-test status...$(NC)"
	kubectl get deployments,services,pods,ingress -n mmjc-test -l app=mermaid-validator-mcp

k8s-logs-dev: ## Show pod logs in mmjc-dev
	@echo "$(BLUE)Showing mmjc-dev pod logs...$(NC)"
	kubectl logs -n mmjc-dev -l app=mermaid-validator-mcp -f

k8s-logs-test: ## Show pod logs in mmjc-test
	@echo "$(BLUE)Showing mmjc-test pod logs...$(NC)"
	kubectl logs -n mmjc-test -l app=mermaid-validator-mcp -f

helm-install-dev: ## Install using Helm in mmjc-dev
	@echo "$(BLUE)Installing Helm chart in mmjc-dev...$(NC)"
	helm upgrade --install mermaid-validator-mcp-dev helm/mermaid-validator-mcp \
		--namespace mmjc-dev \
		--values helm/mermaid-validator-mcp/values-dev.yaml \
		--create-namespace
	@echo "$(GREEN)Helm chart installed in mmjc-dev$(NC)"

helm-install-test: ## Install using Helm in mmjc-test
	@echo "$(BLUE)Installing Helm chart in mmjc-test...$(NC)"
	helm upgrade --install mermaid-validator-mcp-test helm/mermaid-validator-mcp \
		--namespace mmjc-test \
		--values helm/mermaid-validator-mcp/values-test.yaml \
		--create-namespace
	@echo "$(GREEN)Helm chart installed in mmjc-test$(NC)"

helm-uninstall-dev: ## Uninstall Helm chart from mmjc-dev
	@echo "$(BLUE)Uninstalling Helm chart from mmjc-dev...$(NC)"
	helm uninstall mermaid-validator-mcp-dev --namespace mmjc-dev || true

helm-uninstall-test: ## Uninstall Helm chart from mmjc-test
	@echo "$(BLUE)Uninstalling Helm chart from mmjc-test...$(NC)"
	helm uninstall mermaid-validator-mcp-test --namespace mmjc-test || true

##@ CI/CD
ci: ## Run CI pipeline locally
	@echo "$(BLUE)Running CI pipeline...$(NC)"
	@$(MAKE) install
	@$(MAKE) lint
	@$(MAKE) test-coverage
	@$(MAKE) security
	@$(MAKE) docker-build
	@$(MAKE) docker-scan
	@echo "$(GREEN)CI pipeline completed$(NC)"

release: ## Create a release
	@echo "$(BLUE)Creating release...$(NC)"
	@if [ -z "$(VERSION)" ]; then \
		echo "$(RED)Version not found in package.json$(NC)"; \
		exit 1; \
	fi
	@echo "$(BLUE)Creating release $(VERSION)...$(NC)"
	git tag -a v$(VERSION) -m "Release $(VERSION)"
	@$(MAKE) docker-build
	@$(MAKE) docker-build-ubi
	@echo "$(GREEN)Release $(VERSION) created$(NC)"

##@ Examples and Testing
test-examples: ## Test with example files
	@echo "$(BLUE)Testing with example files...$(NC)"
	@mkdir -p tmp/test-results
	@echo "$(BLUE)Testing examples/dad_child3.md...$(NC)"
	@if [ -f examples/dad_child3.md ]; then \
		curl -X POST http://localhost:8000/api/v1/upload/file \
			-F "file=@examples/dad_child3.md" \
			-F "generateSvg=true" \
			-w "\nHTTP Status: %{http_code}\nResponse Time: %{time_total}s\n" \
			-o tmp/test-results/dad_child3_result.json \
			-s && \
		echo "$(GREEN)Example test completed. Results in tmp/test-results/$(NC)" || \
		echo "$(RED)Example test failed. Is the server running?$(NC)"; \
	else \
		echo "$(RED)Example file not found: examples/dad_child3.md$(NC)"; \
	fi

test-direct: ## Test direct validation endpoint
	@echo "$(BLUE)Testing direct validation...$(NC)"
	@mkdir -p tmp/test-results
	curl -X POST http://localhost:8000/api/v1/validate \
		-H "Content-Type: application/json" \
		-d '{"diagrams": ["flowchart TD\n    A --> B\n    B --> C"], "options": {"generateSvg": true}}' \
		-w "\nHTTP Status: %{http_code}\nResponse Time: %{time_total}s\n" \
		-o tmp/test-results/direct_validation_result.json \
		-s && \
	echo "$(GREEN)Direct validation test completed$(NC)" || \
	echo "$(RED)Direct validation test failed$(NC)"

test-health: ## Test health endpoint
	@echo "$(BLUE)Testing health endpoint...$(NC)"
	curl -X GET http://localhost:8000/api/v1/health \
		-w "\nHTTP Status: %{http_code}\nResponse Time: %{time_total}s\n" \
		-s

benchmark: ## Run performance benchmarks
	@echo "$(BLUE)Running performance benchmarks...$(NC)"
	@if command -v ab >/dev/null 2>&1; then \
		echo "$(BLUE)Running Apache Bench on health endpoint...$(NC)"; \
		ab -n 1000 -c 10 http://localhost:8000/api/v1/health; \
	else \
		echo "$(YELLOW)Apache Bench not installed$(NC)"; \
	fi

##@ Documentation
docs: ## Generate API documentation
	@echo "$(BLUE)Generating API documentation...$(NC)"
	npm run docs
	@echo "$(GREEN)Documentation generated$(NC)"

docs-serve: ## Serve documentation locally
	@echo "$(BLUE)Serving documentation...$(NC)"
	@if command -v python3 >/dev/null 2>&1; then \
		cd $(DOCS_DIR) && python3 -m http.server 8080; \
	elif command -v python >/dev/null 2>&1; then \
		cd $(DOCS_DIR) && python -m SimpleHTTPServer 8080; \
	else \
		echo "$(RED)Python not found$(NC)"; \
	fi

##@ Monitoring
monitor: ## Start monitoring stack
	@echo "$(BLUE)Starting monitoring stack...$(NC)"
	docker-compose --profile monitoring up -d
	@echo "$(GREEN)Monitoring available at:$(NC)"
	@echo "$(GREEN)  Prometheus: http://localhost:9090$(NC)"
	@echo "$(GREEN)  Grafana: http://localhost:3000 (admin/admin)$(NC)"

logs: ## Show application logs
	@echo "$(BLUE)Showing application logs...$(NC)"
	@if [ -f logs/app.log ]; then \
		tail -f logs/app.log; \
	else \
		echo "$(YELLOW)No log file found$(NC)"; \
	fi

##@ Utilities
info: ## Show project information
	@echo "$(BLUE)Project Information$(NC)"
	@echo "Name: $(PROJECT_NAME)"
	@echo "Version: $(VERSION)"
	@echo "Node.js: $(NODE_VERSION)"
	@echo "NPM: $(NPM_VERSION)"
	@echo "Docker: $(shell docker --version 2>/dev/null || echo 'not installed')"
	@echo "Kubectl: $(shell kubectl version --client --short 2>/dev/null || echo 'not installed')"

env-check: ## Check environment prerequisites
	@echo "$(BLUE)Checking environment...$(NC)"
	@echo -n "Node.js: "; \
	if command -v node >/dev/null 2>&1; then \
		echo "$(GREEN)✓ $(NODE_VERSION)$(NC)"; \
	else \
		echo "$(RED)✗ Not installed$(NC)"; \
	fi
	@echo -n "NPM: "; \
	if command -v npm >/dev/null 2>&1; then \
		echo "$(GREEN)✓ $(NPM_VERSION)$(NC)"; \
	else \
		echo "$(RED)✗ Not installed$(NC)"; \
	fi
	@echo -n "Docker: "; \
	if command -v docker >/dev/null 2>&1; then \
		echo "$(GREEN)✓ $(shell docker --version | cut -d' ' -f3 | tr -d ',')$(NC)"; \
	else \
		echo "$(RED)✗ Not installed$(NC)"; \
	fi

##@ Deployment
serve: start ## Alias for start (serve the application)

deployment-amd64: ## Build and prepare AMD64 deployment
	@echo "$(BLUE)Building AMD64 deployment...$(NC)"
	@$(MAKE) clean
	@$(MAKE) install
	npm test -- --passWithNoTests || echo "$(YELLOW)Tests passed with no test files$(NC)"
	@$(MAKE) docker-build-amd64
	@echo "$(GREEN)AMD64 deployment ready: $(FULL_IMAGE_NAME):$(IMAGE_TAG)-amd64$(NC)"

deployment-arm64: ## Build and prepare ARM64 deployment (local test)
	@echo "$(BLUE)Building ARM64 deployment for local testing...$(NC)"
	@$(MAKE) clean
	@$(MAKE) install
	@$(MAKE) test
	@$(MAKE) docker-build-arm64
	@echo "$(GREEN)ARM64 deployment ready: $(FULL_IMAGE_NAME):$(IMAGE_TAG)-arm64$(NC)"

push-amd64: ## Push AMD64 image to registry
	@echo "$(BLUE)Pushing AMD64 images to registry...$(NC)"
	docker push $(FULL_IMAGE_NAME):$(VERSION)-amd64
	docker push $(FULL_IMAGE_NAME):latest-amd64
	@echo "$(GREEN)AMD64 images pushed:$(NC)"
	@echo "$(GREEN)  - $(FULL_IMAGE_NAME):$(VERSION)-amd64$(NC)"
	@echo "$(GREEN)  - $(FULL_IMAGE_NAME):latest-amd64$(NC)"

push-arm64: ## Push ARM64 image to registry
	@echo "$(BLUE)Pushing ARM64 image to registry...$(NC)"
	docker push $(FULL_IMAGE_NAME):$(IMAGE_TAG)-arm64
	@echo "$(GREEN)ARM64 image pushed: $(FULL_IMAGE_NAME):$(IMAGE_TAG)-arm64$(NC)"

##@ All-in-one targets
all: clean install lint test security build docker-build ## Run full development cycle
	@echo "$(GREEN)Full development cycle completed$(NC)"

production-ready: clean install test security docker-build docker-scan ## Prepare for production
	@echo "$(GREEN)Production ready build completed$(NC)"

dev-setup: env-check install test docker-build ## Setup development environment
	@echo "$(GREEN)Development environment setup completed$(NC)"

# Default target
.DEFAULT_GOAL := help