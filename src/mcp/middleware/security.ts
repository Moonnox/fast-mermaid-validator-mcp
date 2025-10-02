/**
 * Security middleware for MCP server
 * Implements security best practices and standards
 */

import { Request, Response, NextFunction } from 'express';
import rateLimit from 'express-rate-limit';
import { v4 as uuidv4 } from 'uuid';
const logger = require('../../../src/utils/logger');

export interface SecurityOptions {
  rateLimit?: {
    windowMs?: number;
    max?: number;
    message?: string;
  };
  authentication?: {
    enabled: boolean;
    apiKeyHeader?: string;
    bearerToken?: boolean;
  };
  audit?: {
    enabled: boolean;
    logLevel?: string;
  };
  validation?: {
    maxRequestSize?: string;
    sanitizeInput?: boolean;
  };
}

export class MCPSecurityMiddleware {
  public options: SecurityOptions;

  constructor(options: SecurityOptions = {}) {
    this.options = {
      rateLimit: {
        windowMs: 15 * 60 * 1000, // 15 minutes
        max: 100, // requests per window
        message: 'Too many requests from this IP',
        ...options.rateLimit
      },
      authentication: {
        enabled: false,
        apiKeyHeader: 'x-api-key',
        bearerToken: true,
        ...options.authentication
      },
      audit: {
        enabled: true,
        logLevel: 'info',
        ...options.audit
      },
      validation: {
        maxRequestSize: '10mb',
        sanitizeInput: true,
        ...options.validation
      }
    };
  }

  /**
   * Rate limiting middleware
   */
  getRateLimitMiddleware() {
    return rateLimit({
      windowMs: this.options.rateLimit!.windowMs!,
      max: this.options.rateLimit!.max!,
      message: {
        error: 'Rate limit exceeded',
        message: this.options.rateLimit!.message!,
        retryAfter: Math.ceil(this.options.rateLimit!.windowMs! / 1000)
      },
      standardHeaders: true,
      legacyHeaders: false,
      handler: (req, res) => {
        this.auditLog(req, 'RATE_LIMIT_EXCEEDED', {
          ip: req.ip,
          userAgent: req.get('User-Agent')
        });

        res.status(429).json({
          error: 'Rate limit exceeded',
          message: this.options.rateLimit!.message!,
          retryAfter: Math.ceil(this.options.rateLimit!.windowMs! / 1000),
          timestamp: new Date().toISOString()
        });
      }
    });
  }

  /**
   * Authentication middleware
   */
  getAuthMiddleware() {
    return (req: Request, res: Response, next: NextFunction) => {
      if (!this.options.authentication!.enabled) {
        return next();
      }

      const apiKey = req.get(this.options.authentication!.apiKeyHeader!);
      const authHeader = req.get('Authorization');

      let isAuthenticated = false;
      let authMethod = 'none';

      // Check API key
      if (apiKey) {
        isAuthenticated = this.validateApiKey(apiKey);
        authMethod = 'api_key';
      }

      // Check Bearer token
      if (!isAuthenticated && authHeader && this.options.authentication!.bearerToken) {
        const token = authHeader.replace(/^Bearer\s+/, '');
        if (token) {
          isAuthenticated = this.validateBearerToken(token);
          authMethod = 'bearer_token';
        }
      }

      if (!isAuthenticated) {
        this.auditLog(req, 'AUTHENTICATION_FAILED', {
          ip: req.ip,
          userAgent: req.get('User-Agent'),
          authMethod
        });

        return res.status(401).json({
          error: 'Authentication required',
          message: 'Valid API key or Bearer token required',
          timestamp: new Date().toISOString()
        });
      }

      this.auditLog(req, 'AUTHENTICATION_SUCCESS', {
        ip: req.ip,
        authMethod
      });

      next();
    };
  }

  /**
   * Request validation middleware
   */
  getValidationMiddleware() {
    return (req: Request, res: Response, next: NextFunction) => {
      const requestId = uuidv4();
      (req as any).requestId = requestId;

      this.auditLog(req, 'REQUEST_RECEIVED', {
        requestId,
        method: req.method,
        url: req.url,
        ip: req.ip,
        userAgent: req.get('User-Agent'),
        contentType: req.get('Content-Type')
      });

      // Validate content type for POST requests
      if (req.method === 'POST' && !req.is('application/json')) {
        this.auditLog(req, 'INVALID_CONTENT_TYPE', {
          requestId,
          contentType: req.get('Content-Type')
        });

        return res.status(400).json({
          error: 'Invalid content type',
          message: 'Content-Type must be application/json',
          timestamp: new Date().toISOString()
        });
      }

      // Basic JSON structure validation for MCP requests
      if (req.method === 'POST' && req.body) {
        const validationResult = this.validateMCPRequest(req.body);
        if (!validationResult.valid) {
          this.auditLog(req, 'INVALID_MCP_REQUEST', {
            requestId,
            error: validationResult.error
          });

          return res.status(400).json({
            jsonrpc: '2.0',
            error: {
              code: -32600,
              message: 'Invalid Request',
              data: validationResult.error
            },
            id: req.body?.id || null
          });
        }
      }

      // Input sanitization
      if (this.options.validation!.sanitizeInput && req.body) {
        req.body = this.sanitizeInput(req.body);
      }

      next();
    };
  }

  /**
   * Response middleware for audit logging
   */
  getResponseMiddleware() {
    return (req: Request, res: Response, next: NextFunction) => {
      const originalSend = res.json;
      const requestId = (req as any).requestId;

      res.json = function(body: any) {
        // Log response
        logger.info('MCP response sent', {
          requestId,
          statusCode: res.statusCode,
          responseSize: JSON.stringify(body).length,
          processingTime: Date.now() - (req as any).startTime
        });

        return originalSend.call(this, body);
      };

      (req as any).startTime = Date.now();
      next();
    };
  }

  /**
   * Error handling middleware
   */
  getErrorMiddleware() {
    return (error: Error, req: Request, res: Response, next: NextFunction) => {
      const requestId = (req as any).requestId || uuidv4();

      this.auditLog(req, 'REQUEST_ERROR', {
        requestId,
        error: error.message,
        stack: error.stack,
        method: req.method,
        url: req.url
      });

      // Don't expose internal errors in production
      const isDevelopment = process.env.NODE_ENV === 'development';

      res.status(500).json({
        jsonrpc: '2.0',
        error: {
          code: -32603,
          message: 'Internal error',
          data: isDevelopment ? error.message : 'An internal error occurred'
        },
        id: req.body?.id || null
      });
    };
  }

  /**
   * Validate API key
   */
  private validateApiKey(apiKey: string): boolean {
    // In production, implement proper API key validation
    // For now, accept any non-empty API key
    return !!(apiKey && apiKey.length > 0);
  }

  /**
   * Validate Bearer token
   */
  private validateBearerToken(token: string): boolean {
    // In production, implement proper JWT or token validation
    // For now, accept any non-empty token
    return !!(token && token.length > 0);
  }

  /**
   * Validate MCP request structure
   */
  private validateMCPRequest(body: any): { valid: boolean; error?: string } {
    if (!body || typeof body !== 'object') {
      return { valid: false, error: 'Request body must be a JSON object' };
    }

    if (body.jsonrpc !== '2.0') {
      return { valid: false, error: 'Invalid or missing jsonrpc version' };
    }

    if (!body.method || typeof body.method !== 'string') {
      return { valid: false, error: 'Missing or invalid method' };
    }

    return { valid: true };
  }

  /**
   * Sanitize input to prevent injection attacks
   */
  private sanitizeInput(input: any): any {
    if (typeof input === 'string') {
      // Basic HTML/script tag removal
      return input
        .replace(/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, '')
        .replace(/<[^>]*>/g, '')
        .trim();
    }

    if (Array.isArray(input)) {
      return input.map(item => this.sanitizeInput(item));
    }

    if (input && typeof input === 'object') {
      const sanitized: any = {};
      for (const key in input) {
        if (input.hasOwnProperty(key)) {
          sanitized[key] = this.sanitizeInput(input[key]);
        }
      }
      return sanitized;
    }

    return input;
  }

  /**
   * Audit logging
   */
  private auditLog(req: Request, event: string, data: any) {
    if (!this.options.audit!.enabled) {
      return;
    }

    const auditData = {
      event,
      timestamp: new Date().toISOString(),
      requestId: (req as any).requestId,
      ip: req.ip,
      method: req.method,
      url: req.url,
      userAgent: req.get('User-Agent'),
      ...data
    };

    logger.info('MCP Security Audit', auditData);
  }
}

export default MCPSecurityMiddleware;