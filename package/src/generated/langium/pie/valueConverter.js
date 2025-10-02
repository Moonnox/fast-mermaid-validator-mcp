"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.PieValueConverter = void 0;
const index_js_1 = require("../common/index.js");
class PieValueConverter extends index_js_1.AbstractMermaidValueConverter {
    runCustomConverter(rule, input, _cstNode) {
        if (rule.name !== 'PIE_SECTION_LABEL') {
            return undefined;
        }
        return input.replace(/"/g, '').trim();
    }
}
exports.PieValueConverter = PieValueConverter;
//# sourceMappingURL=valueConverter.js.map