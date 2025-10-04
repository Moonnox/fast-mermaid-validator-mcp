# Release v1.0.31 - Package Rename & Security Fix

## 🚀 Version 1.0.31

### 📦 Package Renamed
- **New Package Name**: `@ai-of-mine/fast-mermaid-validator-mcp`
- **Old Package**: `@ai-of-mine/mermaid-validator-mcp` (now deprecated)

### 🔒 Security Fix
- **Fixed CVE-2025-9230**: Updated libssl3 and libcrypto3 in Docker image to >= 3.5.4-r0
- All Alpine package vulnerabilities resolved

### 🎯 Key Changes
- ✅ Package renamed to `@ai-of-mine/fast-mermaid-validator-mcp`
- ✅ All documentation updated with new package name
- ✅ Directory structure consolidated (removed duplicate example/, tests/, package/ folders)
- ✅ Security vulnerability CVE-2025-9230 fixed in Docker image
- ✅ Old package deprecated with migration message

### 📥 Installation

**NPM Package:**
```bash
npm install @ai-of-mine/fast-mermaid-validator-mcp
npx @ai-of-mine/fast-mermaid-validator-mcp --help
```

**Docker Image:**
```bash
# Build locally with security fix
./docker-build.sh

# Or manually
docker build -t gregoriomomm/fast-mermaid-validator-mcp:1.0.31 .
```

### 🔄 Migration from Old Package

If you're using `@ai-of-mine/mermaid-validator-mcp`, update to:
```bash
npm uninstall @ai-of-mine/mermaid-validator-mcp
npm install @ai-of-mine/fast-mermaid-validator-mcp
```

All CLI commands remain the same, just use the new package name.

### 📝 Commits in this Release
- `3b1016e` - Add Docker build script with multi-arch support and CVE fix
- `3acd156` - Fix CVE-2025-9230: Update libssl3 and libcrypto3 in Docker image
- `a348376` - Bump version to 1.0.31 with updated package name references
- `764e7f5` - Update all references from old to new package name
- `3cacaca` - Rename package to @ai-of-mine/fast-mermaid-validator-mcp v1.0.30
- `5217897` - Clean up: Consolidate directories and remove build artifacts

### 🔗 Links
- **NPM Package**: https://www.npmjs.com/package/@ai-of-mine/fast-mermaid-validator-mcp
- **Repository**: https://github.com/ai-of-mine/fast-mermaid-validator-mcp
- **Documentation**: [README.md](https://github.com/ai-of-mine/fast-mermaid-validator-mcp#readme)

---

## Distribution Checklist

- [x] NPM package published: `@ai-of-mine/fast-mermaid-validator-mcp@1.0.31`
- [x] Old package deprecated with migration message
- [x] All code pushed to GitHub
- [x] Dockerfile updated with CVE-2025-9230 fix
- [ ] Docker image built and pushed to Docker Hub
- [ ] GitHub release created

## Next Steps

1. **Build and Push Docker Image** (requires Docker Desktop running):
   ```bash
   ./docker-build.sh
   ```

2. **Create GitHub Release**:
   - Go to: https://github.com/ai-of-mine/fast-mermaid-validator-mcp/releases/new
   - Tag: `v1.0.31`
   - Title: `v1.0.31 - Package Rename & Security Fix`
   - Copy content from this file as release notes
   - Publish release

3. **Verify Distribution**:
   ```bash
   # Test NPM package
   npx @ai-of-mine/fast-mermaid-validator-mcp --version

   # Test Docker image (after building)
   docker run -p 8000:8000 gregoriomomm/fast-mermaid-validator-mcp:1.0.31
   ```
