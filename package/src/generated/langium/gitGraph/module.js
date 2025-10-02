"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.GitGraphModule = void 0;
exports.createGitGraphServices = createGitGraphServices;
const langium_1 = require("langium");
const valueConverter_js_1 = require("../common/valueConverter.js");
const module_js_1 = require("../generated/module.js");
const tokenBuilder_js_1 = require("./tokenBuilder.js");
exports.GitGraphModule = {
    parser: {
        TokenBuilder: () => new tokenBuilder_js_1.GitGraphTokenBuilder(),
        ValueConverter: () => new valueConverter_js_1.CommonValueConverter(),
    },
};
function createGitGraphServices(context = langium_1.EmptyFileSystem) {
    const shared = (0, langium_1.inject)((0, langium_1.createDefaultSharedCoreModule)(context), module_js_1.MermaidGeneratedSharedModule);
    const GitGraph = (0, langium_1.inject)((0, langium_1.createDefaultCoreModule)({ shared }), module_js_1.GitGraphGeneratedModule, exports.GitGraphModule);
    shared.ServiceRegistry.register(GitGraph);
    return { shared, GitGraph };
}
//# sourceMappingURL=module.js.map