"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TreemapValidator = void 0;
exports.registerValidationChecks = registerValidationChecks;
/**
 * Register custom validation checks.
 */
function registerValidationChecks(services) {
    const validator = services.validation.TreemapValidator;
    const registry = services.validation.ValidationRegistry;
    if (registry) {
        // Use any to bypass type checking since we know Treemap is part of the AST
        // but the type system is having trouble with it
        const checks = {
            Treemap: validator.checkSingleRoot.bind(validator),
            // Remove unused validation for TreemapRow
        };
        registry.register(checks, validator);
    }
}
/**
 * Implementation of custom validations.
 */
class TreemapValidator {
    /**
     * Validates that a treemap has only one root node.
     * A root node is defined as a node that has no indentation.
     */
    checkSingleRoot(doc, accept) {
        let rootNodeIndentation;
        for (const row of doc.TreemapRows) {
            // Skip non-node items or items without a type
            if (!row.item) {
                continue;
            }
            if (rootNodeIndentation === undefined && // Check if this is a root node (no indentation)
                row.indent === undefined) {
                rootNodeIndentation = 0;
            }
            else if (row.indent === undefined) {
                // If we've already found a root node, report an error
                accept('error', 'Multiple root nodes are not allowed in a treemap.', {
                    node: row,
                    property: 'item',
                });
            }
            else if (rootNodeIndentation !== undefined &&
                rootNodeIndentation >= parseInt(row.indent, 10)) {
                accept('error', 'Multiple root nodes are not allowed in a treemap.', {
                    node: row,
                    property: 'item',
                });
            }
        }
    }
}
exports.TreemapValidator = TreemapValidator;
//# sourceMappingURL=treemap-validator.js.map