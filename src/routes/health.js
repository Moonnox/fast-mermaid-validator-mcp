/**
 * Health Check Routes
 * Comprehensive health monitoring endpoints
 */

const express = require('express');
const os = require('os');
const fs = require('fs').promises;
const config = require('../config/config');
const logger = require('../utils/logger');

const router = express.Router();

/**
 * Basic health check endpoint
 * GET /api/v1/health
 */
router.get('/', async (req, res) => {
  try {
    const healthData = await getHealthData();
    
    const status = (healthData.status === 'healthy' || healthData.status === 'degraded') ? 200 : 503;
    
    res.status(status).json({
      status: healthData.status,
      timestamp: new Date().toISOString(),
      uptime: process.uptime(),
      version: process.env.npm_package_version || '1.0.0',
      environment: config.server.env,
      checks: healthData.checks
    });
    
  } catch (error) {
    logger.logError(error, { context: 'health_check' });
    res.status(503).json({
      status: 'unhealthy',
      timestamp: new Date().toISOString(),
      error: 'Health check failed'
    });
  }
});

/**
 * Detailed health check endpoint
 * GET /api/v1/health/detailed
 */
router.get('/detailed', async (req, res) => {
  try {
    const healthData = await getDetailedHealthData();
    
    const status = (healthData.status === 'healthy' || healthData.status === 'degraded') ? 200 : 503;
    
    res.status(status).json(healthData);
    
  } catch (error) {
    logger.logError(error, { context: 'detailed_health_check' });
    res.status(503).json({
      status: 'unhealthy',
      timestamp: new Date().toISOString(),
      error: 'Detailed health check failed'
    });
  }
});

/**
 * Liveness probe endpoint (for Kubernetes)
 * GET /api/v1/health/live
 */
router.get('/live', (req, res) => {
  res.status(200).json({
    status: 'alive',
    timestamp: new Date().toISOString()
  });
});

/**
 * Readiness probe endpoint (for Kubernetes)
 * GET /api/v1/health/ready
 */
router.get('/ready', async (req, res) => {
  try {
    const isReady = await checkReadiness();
    
    if (isReady) {
      res.status(200).json({
        status: 'ready',
        timestamp: new Date().toISOString()
      });
    } else {
      res.status(503).json({
        status: 'not ready',
        timestamp: new Date().toISOString()
      });
    }
    
  } catch (error) {
    logger.logError(error, { context: 'readiness_check' });
    res.status(503).json({
      status: 'not ready',
      timestamp: new Date().toISOString(),
      error: 'Readiness check failed'
    });
  }
});

/**
 * Get basic health data
 * @returns {Object} Health status and basic checks
 */
async function getHealthData() {
  const checks = {};
  let overallStatus = 'healthy';

  // Memory check
  const memoryCheck = await checkMemory();
  checks.memory = memoryCheck;
  if (!memoryCheck.healthy) overallStatus = 'unhealthy';

  // Disk space check (skip in container environment)
  const isContainer = process.env.NODE_ENV === 'production' || process.env.KUBERNETES_SERVICE_HOST;
  if (!isContainer) {
    const diskCheck = await checkDiskSpace();
    checks.disk = diskCheck;
    if (!diskCheck.healthy) overallStatus = 'degraded';
  } else {
    checks.disk = {
      healthy: true,
      message: 'Disk check skipped in container environment'
    };
  }

  // Process check
  const processCheck = checkProcess();
  checks.process = processCheck;
  if (!processCheck.healthy) overallStatus = 'unhealthy';

  return {
    status: overallStatus,
    checks
  };
}

/**
 * Get detailed health data
 * @returns {Object} Comprehensive health information
 */
async function getDetailedHealthData() {
  const basicHealth = await getHealthData();
  
  return {
    ...basicHealth,
    timestamp: new Date().toISOString(),
    uptime: process.uptime(),
    version: process.env.npm_package_version || '1.0.0',
    environment: config.server.env,
    system: {
      platform: os.platform(),
      arch: os.arch(),
      nodeVersion: process.version,
      cpus: os.cpus().length,
      hostname: os.hostname(),
      loadAverage: os.loadavg()
    },
    memory: {
      total: os.totalmem(),
      free: os.freemem(),
      used: os.totalmem() - os.freemem(),
      heap: process.memoryUsage()
    },
    process: {
      pid: process.pid,
      ppid: process.ppid,
      uptime: process.uptime(),
      cwd: process.cwd(),
      execPath: process.execPath,
      argv: process.argv
    }
  };
}

/**
 * Check memory usage
 * @returns {Object} Memory health status
 */
async function checkMemory() {
  const memUsage = process.memoryUsage();
  const totalMem = os.totalmem();
  const freeMem = os.freemem();
  const usedMem = totalMem - freeMem;
  const memoryUsagePercent = (usedMem / totalMem) * 100;

  const threshold = config.health.checks.memory.threshold;
  const healthy = memoryUsagePercent < threshold;

  return {
    healthy,
    usage: {
      percent: Math.round(memoryUsagePercent * 100) / 100,
      used: usedMem,
      total: totalMem,
      free: freeMem,
      heap: memUsage
    },
    threshold,
    message: healthy ? 'Memory usage within limits' : `Memory usage (${memoryUsagePercent.toFixed(1)}%) exceeds threshold (${threshold}%)`
  };
}

/**
 * Check disk space
 * @returns {Object} Disk space health status
 */
async function checkDiskSpace() {
  try {
    const stats = await fs.statfs(process.cwd());
    const total = stats.blocks * stats.blksize;
    const free = stats.bavail * stats.blksize;
    const used = total - free;
    const usagePercent = (used / total) * 100;

    const threshold = config.health.checks.disk.threshold;
    const healthy = usagePercent < threshold;

    return {
      healthy,
      usage: {
        percent: Math.round(usagePercent * 100) / 100,
        used,
        total,
        free
      },
      threshold,
      message: healthy ? 'Disk usage within limits' : `Disk usage (${usagePercent.toFixed(1)}%) exceeds threshold (${threshold}%)`
    };
  } catch (error) {
    return {
      healthy: false,
      error: error.message,
      message: 'Failed to check disk space'
    };
  }
}

/**
 * Check process health
 * @returns {Object} Process health status
 */
function checkProcess() {
  const uptime = process.uptime();
  const memUsage = process.memoryUsage();
  
  // Check if process has been running for at least 5 seconds
  const healthy = uptime > 5;

  return {
    healthy,
    uptime,
    memoryUsage: memUsage,
    pid: process.pid,
    message: healthy ? 'Process running normally' : 'Process just started'
  };
}

/**
 * Check if service is ready to serve requests
 * @returns {boolean} Readiness status
 */
async function checkReadiness() {
  try {
    // Check if temp directory is accessible
    await fs.access(config.upload.tempDir);
    
    // Check basic process health
    const processCheck = checkProcess();
    if (!processCheck.healthy) return false;
    
    // Add more readiness checks as needed
    // e.g., database connections, external services, etc.
    
    return true;
  } catch (error) {
    logger.logError(error, { context: 'readiness_check' });
    return false;
  }
}

module.exports = router;