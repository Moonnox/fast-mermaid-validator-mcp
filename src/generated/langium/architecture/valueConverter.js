"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ArchitectureValueConverter = void 0;
const index_js_1 = require("../common/index.js");
class ArchitectureValueConverter extends index_js_1.AbstractMermaidValueConverter {
    runCustomConverter(rule, input, _cstNode) {
        if (rule.name === 'ARCH_ICON') {
            return input.replace(/[()]/g, '').trim();
        }
        else if (rule.name === 'ARCH_TEXT_ICON') {
            return input.replace(/["()]/g, '');
        }
        else if (rule.name === 'ARCH_TITLE') {
            return input.replace(/[[\]]/g, '').trim();
        }
        return undefined;
    }
}
exports.ArchitectureValueConverter = ArchitectureValueConverter;
//# sourceMappingURL=valueConverter.js.map