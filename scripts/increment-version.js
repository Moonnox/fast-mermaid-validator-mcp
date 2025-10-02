#!/usr/bin/env node

/**
 * Automatic Version Increment Script
 * Increments package version based on type (patch, minor, major)
 * Author: Gregorio Elias Roecker Momm
 */

const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

const COLORS = {
  RED: '\x1b[31m',
  GREEN: '\x1b[32m',
  YELLOW: '\x1b[33m',
  BLUE: '\x1b[34m',
  NC: '\x1b[0m'
};

function log(message, color = COLORS.NC) {
  console.log(`${color}${message}${COLORS.NC}`);
}

/**
 * Parse semantic version
 */
function parseVersion(version) {
  const [major, minor, patch] = version.split('.').map(Number);
  return { major, minor, patch };
}

/**
 * Increment version based on type
 */
function incrementVersion(currentVersion, type = 'patch') {
  const { major, minor, patch } = parseVersion(currentVersion);
  
  switch (type) {
    case 'major':
      return `${major + 1}.0.0`;
    case 'minor':
      return `${major}.${minor + 1}.0`;
    case 'patch':
    default:
      return `${major}.${minor}.${patch + 1}`;
  }
}

/**
 * Update package.json with new version
 */
function updatePackageVersion(newVersion) {
  const packagePath = path.join(__dirname, '../package.json');
  const packageJson = JSON.parse(fs.readFileSync(packagePath, 'utf8'));
  
  const oldVersion = packageJson.version;
  packageJson.version = newVersion;
  
  fs.writeFileSync(packagePath, JSON.stringify(packageJson, null, 2) + '\n');
  
  return oldVersion;
}

/**
 * Generate build timestamp
 */
function getBuildTimestamp() {
  return new Date().toISOString().replace(/[:.]/g, '-').split('T')[0];
}

/**
 * Get git commit hash
 */
function getGitCommit() {
  try {
    return execSync('git rev-parse --short HEAD', { encoding: 'utf8' }).trim();
  } catch {
    return 'unknown';
  }
}

/**
 * Main version increment function
 */
function main() {
  const args = process.argv.slice(2);
  const incrementType = args[0] || 'patch';
  const buildType = args[1] || 'standard';
  
  log('Automatic Version Increment', COLORS.BLUE);
  log('=========================\n', COLORS.BLUE);
  
  // Read current version
  const packagePath = path.join(__dirname, '../package.json');
  const packageJson = JSON.parse(fs.readFileSync(packagePath, 'utf8'));
  const currentVersion = packageJson.version;
  
  // Generate new version
  let newVersion;
  
  if (buildType === 'docker') {
    // For Docker builds, add build metadata
    const baseVersion = incrementVersion(currentVersion, incrementType);
    const buildTimestamp = getBuildTimestamp();
    const gitCommit = getGitCommit();
    
    // Create version with build metadata
    newVersion = `${baseVersion}-${buildTimestamp}-${gitCommit}`;
    
    log(`Docker build version: ${newVersion}`, COLORS.BLUE);
    
    // Don't update package.json for Docker builds, just return the version
    console.log(newVersion);
    return;
    
  } else {
    // Standard version increment
    newVersion = incrementVersion(currentVersion, incrementType);
  }
  
  // Update package.json
  const oldVersion = updatePackageVersion(newVersion);
  
  log(`Version incremented: ${oldVersion} → ${newVersion}`, COLORS.GREEN);
  log(`Increment type: ${incrementType}`, COLORS.BLUE);
  
  // Generate changelog entry
  const changelogEntry = `
## [${newVersion}] - ${new Date().toISOString().split('T')[0]}

### Changed
- Automatic version increment (${incrementType})
- Build timestamp: ${new Date().toISOString()}
- Git commit: ${getGitCommit()}

`;
  
  // Try to update CHANGELOG.md if it exists
  const changelogPath = path.join(__dirname, '../CHANGELOG.md');
  if (fs.existsSync(changelogPath)) {
    const changelog = fs.readFileSync(changelogPath, 'utf8');
    const updatedChangelog = changelog.replace(
      '# Changelog',
      `# Changelog${changelogEntry}`
    );
    fs.writeFileSync(changelogPath, updatedChangelog);
    log('Updated CHANGELOG.md', COLORS.GREEN);
  }
  
  log(`\nNew version: ${newVersion}`, COLORS.GREEN);
  log(`Ready for: git tag v${newVersion}`, COLORS.YELLOW);
}

// Run if called directly
if (require.main === module) {
  main();
}

module.exports = {
  incrementVersion,
  updatePackageVersion,
  getBuildTimestamp,
  getGitCommit
};