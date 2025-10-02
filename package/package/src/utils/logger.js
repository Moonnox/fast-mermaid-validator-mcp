/**
 * Centralized Logging Configuration
 * Production-ready logging with Winston
 */

const winston = require('winston');
const path = require('path');
const config = require('../config/config');

// Custom log format
const logFormat = winston.format.combine(
  winston.format.timestamp({ format: 'YYYY-MM-DD HH:mm:ss' }),
  winston.format.errors({ stack: true }),
  winston.format.json(),
  winston.format.printf(({ timestamp, level, message, stack, ...meta }) => {
    const logObject = {
      timestamp,
      level,
      message,
      ...meta
    };
    
    if (stack) {
      logObject.stack = stack;
    }
    
    return JSON.stringify(logObject);
  })
);

// Console format for development
const consoleFormat = winston.format.combine(
  winston.format.colorize(),
  winston.format.timestamp({ format: 'HH:mm:ss' }),
  winston.format.printf(({ timestamp, level, message, ...meta }) => {
    const metaStr = Object.keys(meta).length ? JSON.stringify(meta, null, 2) : '';
    return `${timestamp} [${level}]: ${message} ${metaStr}`;
  })
);

// Create transports array
const transports = [
  new winston.transports.Console({
    format: config.server.env === 'development' ? consoleFormat : logFormat,
    level: config.logging.level
  })
];

// Add file transport if enabled
if (config.logging.file.enabled) {
  // Ensure logs directory exists
  const fs = require('fs');
  const logDir = path.dirname(config.logging.file.filename);
  if (!fs.existsSync(logDir)) {
    fs.mkdirSync(logDir, { recursive: true });
  }

  transports.push(
    new winston.transports.File({
      filename: config.logging.file.filename,
      format: logFormat,
      maxFiles: config.logging.file.maxFiles,
      maxsize: config.logging.file.maxSize,
      level: config.logging.level
    })
  );
}

// Create logger instance
const logger = winston.createLogger({
  level: config.logging.level,
  format: logFormat,
  transports,
  exitOnError: false
});

// Helper methods for structured logging
logger.logRequest = (req, res, responseTime) => {
  logger.info('HTTP Request', {
    method: req.method,
    url: req.url,
    statusCode: res.statusCode,
    responseTime: `${responseTime}ms`,
    userAgent: req.get('User-Agent'),
    ip: req.ip,
    contentLength: res.get('Content-Length')
  });
};

logger.logValidation = (results, processingTime) => {
  logger.info('Validation completed', {
    totalDiagrams: results.totalDiagrams,
    validDiagrams: results.validDiagrams,
    invalidDiagrams: results.invalidDiagrams,
    processingTime: `${processingTime}ms`,
    errorCount: results.results.reduce((acc, r) => acc + r.errors.length, 0)
  });
};

logger.logError = (error, context = {}) => {
  logger.error('Application error', {
    message: error.message,
    stack: error.stack,
    ...context
  });
};

logger.logSecurity = (event, details = {}) => {
  logger.warn('Security event', {
    event,
    ...details
  });
};

module.exports = logger;