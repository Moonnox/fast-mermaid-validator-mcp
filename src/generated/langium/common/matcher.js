"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.titleRegex = exports.accessibilityTitleRegex = exports.accessibilityDescrRegex = void 0;
/**
 * Matches single and multi line accessible description
 */
exports.accessibilityDescrRegex = /accDescr(?:[\t ]*:([^\n\r]*)|\s*{([^}]*)})/;
/**
 * Matches single line accessible title
 */
exports.accessibilityTitleRegex = /accTitle[\t ]*:([^\n\r]*)/;
/**
 * Matches a single line title
 */
exports.titleRegex = /title([\t ][^\n\r]*|)/;
//# sourceMappingURL=matcher.js.map