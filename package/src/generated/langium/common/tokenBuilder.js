"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CommonTokenBuilder = exports.AbstractMermaidTokenBuilder = void 0;
const langium_1 = require("langium");
class AbstractMermaidTokenBuilder extends langium_1.DefaultTokenBuilder {
    constructor(keywords) {
        super();
        this.keywords = new Set(keywords);
    }
    buildKeywordTokens(rules, terminalTokens, options) {
        const tokenTypes = super.buildKeywordTokens(rules, terminalTokens, options);
        // to restrict users, they mustn't have any non-whitespace characters after the keyword.
        tokenTypes.forEach((tokenType) => {
            if (this.keywords.has(tokenType.name) && tokenType.PATTERN !== undefined) {
                // eslint-disable-next-line @typescript-eslint/no-base-to-string
                tokenType.PATTERN = new RegExp(tokenType.PATTERN.toString() + '(?:(?=%%)|(?!\\S))');
            }
        });
        return tokenTypes;
    }
}
exports.AbstractMermaidTokenBuilder = AbstractMermaidTokenBuilder;
class CommonTokenBuilder extends AbstractMermaidTokenBuilder {
}
exports.CommonTokenBuilder = CommonTokenBuilder;
//# sourceMappingURL=tokenBuilder.js.map