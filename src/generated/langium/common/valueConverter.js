"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CommonValueConverter = exports.AbstractMermaidValueConverter = void 0;
const langium_1 = require("langium");
const matcher_js_1 = require("./matcher.js");
const rulesRegexes = {
    ACC_DESCR: matcher_js_1.accessibilityDescrRegex,
    ACC_TITLE: matcher_js_1.accessibilityTitleRegex,
    TITLE: matcher_js_1.titleRegex,
};
class AbstractMermaidValueConverter extends langium_1.DefaultValueConverter {
    runConverter(rule, input, cstNode) {
        let value = this.runCommonConverter(rule, input, cstNode);
        if (value === undefined) {
            value = this.runCustomConverter(rule, input, cstNode);
        }
        if (value === undefined) {
            return super.runConverter(rule, input, cstNode);
        }
        return value;
    }
    runCommonConverter(rule, input, _cstNode) {
        const regex = rulesRegexes[rule.name];
        if (regex === undefined) {
            return undefined;
        }
        const match = regex.exec(input);
        if (match === null) {
            return undefined;
        }
        // single line title, accTitle, accDescr
        if (match[1] !== undefined) {
            return match[1].trim().replace(/[\t ]{2,}/gm, ' ');
        }
        // multi line accDescr
        if (match[2] !== undefined) {
            return match[2]
                .replace(/^\s*/gm, '')
                .replace(/\s+$/gm, '')
                .replace(/[\t ]{2,}/gm, ' ')
                .replace(/[\n\r]{2,}/gm, '\n');
        }
        return undefined;
    }
}
exports.AbstractMermaidValueConverter = AbstractMermaidValueConverter;
class CommonValueConverter extends AbstractMermaidValueConverter {
    runCustomConverter(_rule, _input, _cstNode) {
        return undefined;
    }
}
exports.CommonValueConverter = CommonValueConverter;
//# sourceMappingURL=valueConverter.js.map