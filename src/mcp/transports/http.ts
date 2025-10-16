/**
 * HTTP Streamable Transport for MCP Server
 * Provides HTTP-based transport with streaming support
 */

import express, { Request, Response } from 'express';
import { createServer, Server as HttpServer } from 'http';
import { MermaidValidatorMCPServer } from '../server.js';
import { MCPSecurityMiddleware } from '../middleware/security.js';
const logger = require('../../../src/utils/logger');

export interface HttpTransportOptions {
  port: number;
  host?: string;
  enableSSE?: boolean;
  cors?: {
    origin?: string | string[];
    credentials?: boolean;
  };
}

export class HttpTransport {
  private app: express.Application;
  private server: HttpServer | null = null;
  private mcpServer: MermaidValidatorMCPServer;
  private options: HttpTransportOptions;
  private sseClients: Map<string, Response> = new Map();
  private securityMiddleware: MCPSecurityMiddleware;

  constructor(mcpServer: MermaidValidatorMCPServer, options: HttpTransportOptions) {
    this.mcpServer = mcpServer;
    this.options = options;
    this.app = express();
    this.securityMiddleware = new MCPSecurityMiddleware();
    this.setupMiddleware();
    this.setupRoutes();
  }

  private setupMiddleware(): void {
    // Basic middleware
    this.app.use(express.json({ limit: '10mb' }));
    this.app.use(express.urlencoded({ extended: true, limit: '10mb' }));

    // CORS configuration
    if (this.options.cors) {
      this.app.use((req, res, next) => {
        const origin = this.options.cors?.origin;
        if (origin) {
          if (Array.isArray(origin)) {
            const requestOrigin = req.headers.origin;
            if (requestOrigin && origin.includes(requestOrigin)) {
              res.header('Access-Control-Allow-Origin', requestOrigin);
            }
          } else {
            res.header('Access-Control-Allow-Origin', origin);
          }
        }

        res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
        res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization, x-secret-key');

        if (this.options.cors?.credentials) {
          res.header('Access-Control-Allow-Credentials', 'true');
        }

        if (req.method === 'OPTIONS') {
          res.sendStatus(200);
        } else {
          next();
        }
      });
    }

    // Request logging
    this.app.use((req, res, next) => {
      logger.info('HTTP request', {
        method: req.method,
        url: req.url,
        userAgent: req.get('User-Agent'),
        ip: req.ip
      });
      next();
    });

    // Secret key validation middleware
    this.app.use(this.securityMiddleware.getSecretKeyMiddleware());
  }

  private setupRoutes(): void {
    // Health check endpoint
    this.app.get('/health', (req, res) => {
      res.json({
        status: 'healthy',
        server: 'mermaid-validator-mcp',
        version: '1.0.0',
        transport: 'http',
        timestamp: new Date().toISOString()
      });
    });

    // MCP endpoint - main JSON-RPC interface
    this.app.post('/mcp', async (req, res) => {
      try {
        await this.handleMCPRequest(req, res);
      } catch (error) {
        logger.error('HTTP MCP request error', {
          error: (error as Error).message,
          context: 'http_mcp_request',
          method: req.method,
          url: req.url
        });

        res.status(500).json({
          jsonrpc: '2.0',
          error: {
            code: -32603,
            message: 'Internal error',
            data: (error as Error).message
          },
          id: req.body?.id || null
        });
      }
    });

    // SSE endpoint for streaming (if enabled)
    if (this.options.enableSSE) {
      this.app.get('/mcp/stream', (req, res) => {
        this.handleSSEConnection(req, res);
      });
    }

    // Info endpoint
    this.app.get('/info', (req, res) => {
      res.json({
        server: {
          name: 'mermaid-validator-mcp',
          version: '1.0.0',
          protocol: 'Model Context Protocol',
          transport: 'http'
        },
        capabilities: {
          tools: true,
          resources: true,
          streaming: this.options.enableSSE
        },
        endpoints: {
          mcp: '/mcp',
          health: '/health',
          stream: this.options.enableSSE ? '/mcp/stream' : null
        },
        timestamp: new Date().toISOString()
      });
    });

    // 404 handler
    this.app.use('*', (req, res) => {
      res.status(404).json({
        error: 'Not Found',
        message: `Endpoint ${req.method} ${req.originalUrl} not found`,
        availableEndpoints: ['/health', '/info', '/mcp'],
        timestamp: new Date().toISOString()
      });
    });
  }

  private async handleMCPRequest(req: Request, res: Response): Promise<void> {
    const requestData = req.body;

    // Check if this is an SSE client request (has sessionId header)
    const sessionId = req.headers['x-session-id'] as string || req.query.sessionId as string;
    const isSSERequest = sessionId && this.sseClients.has(sessionId);

    // Validate JSON-RPC format
    if (!requestData || typeof requestData !== 'object') {
      const errorResponse = {
        jsonrpc: '2.0',
        error: {
          code: -32700,
          message: 'Parse error'
        },
        id: null
      };

      if (isSSERequest) {
        this.sendSSEResponse(sessionId, errorResponse);
        res.status(204).end();
      } else {
        res.status(400).json(errorResponse);
      }
      return;
    }

    if (!requestData.jsonrpc || requestData.jsonrpc !== '2.0') {
      const errorResponse = {
        jsonrpc: '2.0',
        error: {
          code: -32600,
          message: 'Invalid Request'
        },
        id: requestData.id || null
      };

      if (isSSERequest) {
        this.sendSSEResponse(sessionId, errorResponse);
        res.status(204).end();
      } else {
        res.status(400).json(errorResponse);
      }
      return;
    }

    try {
      const response = await this.processMCPMessage(requestData);

      // Don't send response for notifications
      if (response === null) {
        res.status(204).end(); // No Content for notifications
        return;
      }

      if (isSSERequest) {
        // Send response through SSE stream
        this.sendSSEResponse(sessionId, response);
        res.status(204).end(); // Acknowledge HTTP request
      } else {
        // Send normal HTTP response
        res.json(response);
      }
    } catch (error) {
      const errorResponse = {
        jsonrpc: '2.0',
        error: {
          code: -32603,
          message: 'Internal error',
          data: (error as Error).message
        },
        id: requestData.id || null
      };

      if (isSSERequest) {
        this.sendSSEResponse(sessionId, errorResponse);
        res.status(204).end();
      } else {
        res.status(500).json(errorResponse);
      }
    }
  }

  private sendSSEResponse(sessionId: string, response: any): void {
    const sseClient = this.sseClients.get(sessionId);
    if (sseClient) {
      try {
        sseClient.write('event: message\n');
        sseClient.write(`data: ${JSON.stringify(response)}\n\n`);
        logger.info('Sent MCP response via SSE', { sessionId, method: response.result ? 'success' : 'error' });
      } catch (error) {
        logger.error('Failed to send SSE response', {
          sessionId,
          error: (error as Error).message
        });
        this.sseClients.delete(sessionId);
      }
    } else {
      logger.warn('SSE client not found for session', { sessionId });
    }
  }

  private async processMCPMessage(message: any): Promise<any> {
    const { method, params, id } = message;

    switch (method) {
      case 'initialize':
        return {
          jsonrpc: '2.0',
          result: {
            protocolVersion: '2024-11-05',
            capabilities: {
              tools: { listChanged: true },
              resources: { listChanged: true },
              completions: {}
            },
            serverInfo: {
              name: 'mermaid-validator',
              version: '1.0.0'
            }
          },
          id
        };

      case 'resources/list':
        return {
          jsonrpc: '2.0',
          result: this.getResourcesList(),
          id
        };

      case 'resources/read':
        const resourceUri = params?.uri;
        try {
          const resource = await this.readResource(resourceUri);
          return {
            jsonrpc: '2.0',
            result: resource,
            id
          };
        } catch (error) {
          return {
            jsonrpc: '2.0',
            error: {
              code: -32603,
              message: 'Resource read failed',
              data: (error as Error).message
            },
            id
          };
        }

      case 'notifications/initialized':
        // Handle initialization notification (no response needed)
        return null; // Notification methods don't return responses

      case 'tools/list':
        return {
          jsonrpc: '2.0',
          result: {
            tools: [
              {
                name: 'validate-diagrams',
                title: 'Validate Mermaid Diagrams',
                description: 'Validate individual Mermaid diagram code blocks using grammar parsers. Pass raw diagram code (e.g., "flowchart TD\\n A --> B") to check syntax, structure and validity. Returns detailed validation results including errors, warnings, and diagram type detection. Supports all 28+ diagram types: flowchart, sequence, class, state, ER, gantt, journey, timeline, gitgraph, C4, mindmap, requirement, architecture, and more.',
                inputSchema: {
                  type: 'object',
                  properties: {
                    diagrams: {
                      type: 'array',
                      description: 'Array of diagram objects to validate',
                      items: {
                        type: 'object',
                        properties: {
                          content: {
                            type: 'string',
                            description: 'Raw Mermaid diagram code (without ```mermaid wrapper)'
                          },
                          id: {
                            type: 'string',
                            description: 'Optional identifier for the diagram'
                          },
                          type: {
                            type: 'string',
                            description: 'Optional diagram type hint (flowchart, sequence, etc.)'
                          }
                        },
                        required: ['content']
                      }
                    }
                  },
                  required: ['diagrams']
                }
              },
              {
                name: 'validate-files',
                title: 'Validate ZIP Archives and Markdown Files with Mermaid Diagrams',
                description: 'Process and validate Mermaid diagrams from multiple sources: individual markdown files OR Base64-encoded ZIP archives containing multiple markdown files. Automatically extracts ZIP contents, finds all .md files, locates ```mermaid code blocks, validates diagram syntax, and provides comprehensive results with file paths and line numbers. Perfect for bulk validation, CI/CD pipeline checks, documentation repositories, and content verification workflows.',
                inputSchema: {
                  type: 'object',
                  properties: {
                    files: {
                      type: 'array',
                      description: 'Array of files to process - can be individual markdown files or ZIP archives',
                      items: {
                        type: 'object',
                        properties: {
                          fileName: {
                            type: 'string',
                            description: 'File name with extension: "README.md" for markdown, "docs.zip" for ZIP archives, "folder/file.md" for nested files'
                          },
                          content: {
                            type: 'string',
                            description: 'File content: markdown text for .md files, OR Base64-encoded ZIP file data containing multiple markdown files'
                          },
                          encoding: {
                            type: 'string',
                            enum: ['utf8', 'base64'],
                            description: 'Content encoding: "utf8" for plain markdown text, "base64" for ZIP file binary data'
                          }
                        },
                        required: ['fileName', 'content']
                      }
                    }
                  },
                  required: ['files']
                }
              },
              {
                name: 'get-validation-stats',
                title: 'Get Server Capabilities and Statistics',
                description: 'Retrieve comprehensive information about the Mermaid validator server including: supported diagram types (28+ types), available grammar parsers, server configuration, validation limits, performance statistics, and transport capabilities. Useful for understanding what diagram types can be validated and server operational status.',
                inputSchema: {
                  type: 'object',
                  properties: {},
                  description: 'No parameters required - returns complete server information'
                }
              }
            ]
          },
          id
        };

      case 'tools/call':
        // Delegate to actual MCP server tools
        const toolName = params?.name;
        const toolArgs = params?.arguments || {};

        try {
          const result = await this.callMCPTool(toolName, toolArgs);
          return {
            jsonrpc: '2.0',
            result: {
              content: [
                {
                  type: 'text',
                  text: JSON.stringify(result, null, 2)
                }
              ]
            },
            id
          };
        } catch (error) {
          return {
            jsonrpc: '2.0',
            error: {
              code: -32603,
              message: 'Tool execution failed',
              data: (error as Error).message
            },
            id
          };
        }

      default:
        throw new Error(`Unknown method: ${method}`);
    }
  }

  private handleSSEConnection(req: Request, res: Response): void {
    // Generate session ID for this SSE connection
    const sessionId = this.generateSessionId();

    // Set SSE headers
    res.writeHead(200, {
      'Content-Type': 'text/event-stream',
      'Cache-Control': 'no-cache',
      'Connection': 'keep-alive',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Cache-Control'
    });

    // Store the SSE client connection
    this.sseClients.set(sessionId, res);

    // Send required endpoint event first (MCP specification requirement)
    res.write('event: endpoint\n');
    res.write(`data: http://${this.options.host || 'localhost'}:${this.options.port}/mcp?sessionId=${sessionId}\n\n`);

    // Send session event with the session ID
    res.write('event: session\n');
    res.write(`data: ${JSON.stringify({ sessionId })}\n\n`);

    // Send initial connection event
    res.write('event: connected\n');
    res.write(`data: ${JSON.stringify({
      message: 'Connected to MCP server stream',
      sessionId,
      timestamp: new Date().toISOString()
    })}\n\n`);

    // Keep connection alive
    const keepAlive = setInterval(() => {
      if (this.sseClients.has(sessionId)) {
        res.write('event: ping\n');
        res.write(`data: ${JSON.stringify({ timestamp: new Date().toISOString() })}\n\n`);
      }
    }, 30000);

    // Handle client disconnect
    req.on('close', () => {
      clearInterval(keepAlive);
      this.sseClients.delete(sessionId);
      logger.info('SSE client disconnected', { sessionId });
    });

    logger.info('SSE client connected', { sessionId });
  }

  private generateSessionId(): string {
    return 'sse_' + Math.random().toString(36).substring(2) + Date.now().toString(36);
  }

  async start(): Promise<void> {
    return new Promise((resolve, reject) => {
      this.server = createServer(this.app);

      this.server.listen(this.options.port, this.options.host || 'localhost', () => {
        logger.info('HTTP transport started', {
          port: this.options.port,
          host: this.options.host || 'localhost',
          sseEnabled: this.options.enableSSE,
          endpoints: {
            mcp: `/mcp`,
            health: `/health`,
            info: `/info`,
            stream: this.options.enableSSE ? `/mcp/stream` : null
          }
        });
        resolve();
      });

      this.server.on('error', (error) => {
        logger.error('HTTP transport startup error', { error: error.message, context: 'http_transport_startup' });
        reject(error);
      });
    });
  }

  async stop(): Promise<void> {
    if (this.server) {
      return new Promise((resolve) => {
        this.server!.close(() => {
          logger.info('HTTP transport stopped');
          resolve();
        });
      });
    }
  }

  /**
   * Get resources list
   */
  private getResourcesList() {
    return {
      resources: [
        {
          uri: "config://limits",
          name: "config-limits",
          title: "Validation Limits",
          description: "Current validation limits and configuration"
        },
        {
          uri: "info://diagram-types",
          name: "diagram-types",
          title: "Supported Diagram Types",
          description: "List of supported Mermaid diagram types"
        }
      ]
    };
  }

  /**
   * Read a specific resource
   */
  private async readResource(uri: string): Promise<any> {
    const validator = (this.mcpServer as any).validator;
    const config = require('../../../src/config/config');

    switch (uri) {
      case 'config://limits':
        return {
          contents: [{
            uri: uri,
            text: JSON.stringify({
              maxFileSize: config.upload?.maxFileSize || 1048576,
              maxFiles: config.upload?.maxFiles || 10,
              maxDiagramsPerFile: config.validation?.maxDiagramsPerFile || 50,
              maxTotalDiagrams: config.validation?.maxTotalDiagrams || 100,
              validationTimeout: config.validation?.timeout || 30000
            }, null, 2),
            mimeType: "application/json"
          }]
        };

      case 'info://diagram-types':
        return {
          contents: [{
            uri: uri,
            text: JSON.stringify({
              supportedTypes: validator.getSupportedTypes()
            }, null, 2),
            mimeType: "application/json"
          }]
        };

      default:
        throw new Error(`Unknown resource: ${uri}`);
    }
  }

  /**
   * Call actual MCP tools from HTTP transport
   */
  private async callMCPTool(toolName: string, toolArgs: any): Promise<any> {
    const { handleValidateDiagrams } = require('../tools/validateDiagrams.js');
    const { handleValidateFiles } = require('../tools/validateFiles.js');
    const { handleGetStats } = require('../tools/getStats.js');

    // Get instances from the MCP server
    const validator = (this.mcpServer as any).validator;
    const fileProcessor = (this.mcpServer as any).fileProcessor;

    switch (toolName) {
      case 'validate-diagrams':
        return await handleValidateDiagrams(toolArgs, validator, fileProcessor);

      case 'validate-files':
        return await handleValidateFiles(toolArgs, validator, fileProcessor);

      case 'get-validation-stats':
        return await handleGetStats(validator);

      default:
        throw new Error(`Unknown tool: ${toolName}`);
    }
  }
}