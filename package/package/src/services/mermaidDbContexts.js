
/**
 * Complete Mermaid Parser Contexts with Original Functions
 * Extracted from Mermaid.js v10+ source code
 * Author: Gregorio Elias Roecker Momm
 */

// C4 Diagram Functions
const c4Functions = {
  c4ShapeArray: [],
  boundaryParseStack: [''],
  currentBoundaryParse: 'global',
  parentBoundaryParse: '',
  boundaries: [{ alias: 'global', label: { text: 'global' }, type: { text: 'global' }, tags: null, link: null, parentBoundary: '' }],
  rels: [],
  title: '',
  wrapEnabled: false,
  c4ShapeInRow: 4,
  c4BoundaryInRow: 2,
  c4Type: null,
  
  addPersonOrSystem: function(typeC4Shape, alias, label, descr, sprite, tags, link) {
    // Don't allow label nulling
    if (alias === null || label === null) {
      return;
    }

    let personOrSystem = {};
    const old = this.c4ShapeArray.find((personOrSystem) => personOrSystem.alias === alias);
    if (old && alias === old.alias) {
      personOrSystem = old;
    } else {
      personOrSystem.alias = alias;
      this.c4ShapeArray.push(personOrSystem);
    }

    // Don't allow null labels, either
    if (label === undefined || label === null) {
      personOrSystem.label = { text: '' };
    } else {
      personOrSystem.label = { text: label };
    }

    if (descr === undefined || descr === null) {
      personOrSystem.descr = { text: '' };
    } else {
      if (typeof descr === 'object') {
        let [key, value] = Object.entries(descr)[0];
        personOrSystem[key] = { text: value };
      } else {
        personOrSystem.descr = { text: descr };
      }
    }

    if (typeof sprite === 'object') {
      let [key, value] = Object.entries(sprite)[0];
      personOrSystem[key] = value;
    } else {
      personOrSystem.sprite = sprite;
    }
    if (typeof tags === 'object') {
      let [key, value] = Object.entries(tags)[0];
      personOrSystem[key] = value;
    } else {
      personOrSystem.tags = tags;
    }
    if (typeof link === 'object') {
      let [key, value] = Object.entries(link)[0];
      personOrSystem[key] = value;
    } else {
      personOrSystem.link = link;
    }
    personOrSystem.typeC4Shape = { text: typeC4Shape };
    personOrSystem.parentBoundary = this.currentBoundaryParse;
    personOrSystem.wrap = this.autoWrap();
    return personOrSystem;
  },
  
  addPersonOrSystemBoundary: function(alias, label, type, tags, link) {
    // Don't allow label nulling
    if (alias === null || label === null) {
      return;
    }

    let boundary = {};
    const old = this.boundaries.find((boundary) => boundary.alias === alias);
    if (old && alias === old.alias) {
      boundary = old;
    } else {
      boundary.alias = alias;
      this.boundaries.push(boundary);
    }

    // Don't allow null labels, either
    if (label === undefined || label === null) {
      boundary.label = { text: '' };
    } else {
      boundary.label = { text: label };
    }

    if (type === undefined || type === null) {
      boundary.type = { text: 'system' };
    } else {
      if (typeof type === 'object') {
        let [key, value] = Object.entries(type)[0];
        boundary[key] = { text: value };
      } else {
        boundary.type = { text: type };
      }
    }

    if (typeof tags === 'object') {
      let [key, value] = Object.entries(tags)[0];
      boundary[key] = value;
    } else {
      boundary.tags = tags;
    }
    if (typeof link === 'object') {
      let [key, value] = Object.entries(link)[0];
      boundary[key] = value;
    } else {
      boundary.link = link;
    }
    boundary.parentBoundary = this.currentBoundaryParse;
    boundary.wrap = this.autoWrap();

    this.parentBoundaryParse = this.currentBoundaryParse;
    this.currentBoundaryParse = alias;
    this.boundaryParseStack.push(this.parentBoundaryParse);
    return boundary;
  },
  
  addContainer: function(typeC4Shape, alias, label, techn, descr, sprite, tags, link) {
    if (!alias || !label) return;
    let container = this.c4ShapeArray.find(s => s.alias === alias) || { alias };
    if (!this.c4ShapeArray.includes(container)) this.c4ShapeArray.push(container);
    
    container.label = { text: label };
    container.techn = { text: techn || '' };
    container.descr = { text: descr || '' };
    container.sprite = sprite;
    container.tags = tags;
    container.link = link;
    container.typeC4Shape = { text: typeC4Shape };
    container.parentBoundary = this.currentBoundaryParse;
    return container;
  },
  
  addRel: function(type, from, to, label, techn, descr, sprite, tags, link) {
    if (!type || !from || !to || !label) return;
    this.rels.push({ type, from, to, label: { text: label }, techn: { text: techn || '' }, descr: { text: descr || '' }, sprite, tags, link });
  },
  
  setC4Type: function(type) { this.c4Type = type; },
  getC4Type: function() { return this.c4Type; },
  
  autoWrap: function() {
    return this.wrapEnabled;
  },
  
  setWrap: function(wrapSetting) {
    this.wrapEnabled = wrapSetting;
  },
  
  popBoundaryParseStack: function() {
    this.currentBoundaryParse = this.parentBoundaryParse;
    this.boundaryParseStack.pop();
    this.parentBoundaryParse = this.boundaryParseStack.pop();
    this.boundaryParseStack.push(this.parentBoundaryParse);
  },
  
  clear: function() {
    this.c4ShapeArray = [];
    this.boundaries = [
      {
        alias: 'global',
        label: { text: 'global' },
        type: { text: 'global' },
        tags: null,
        link: null,
        parentBoundary: '',
      },
    ];
    this.parentBoundaryParse = '';
    this.currentBoundaryParse = 'global';
    this.boundaryParseStack = [''];
    this.rels = [];
    this.title = '';
    this.wrapEnabled = false;
    this.c4ShapeInRow = 4;
    this.c4BoundaryInRow = 2;
    this.c4Type = null;
  }
};

// Quadrant Chart Functions  
const quadrantFunctions = {
  xAxisLeftText: '',
  xAxisRightText: '',
  yAxisTopText: '',
  yAxisBottomText: '',
  quadrant1Text: '',
  quadrant2Text: '',
  quadrant3Text: '',
  quadrant4Text: '',
  points: [],
  
  setXAxisLeftText: function(text) { this.xAxisLeftText = text; },
  setXAxisRightText: function(text) { this.xAxisRightText = text; },
  setYAxisTopText: function(text) { this.yAxisTopText = text; },
  setYAxisBottomText: function(text) { this.yAxisBottomText = text; },
  setQuadrant1Text: function(text) { this.quadrant1Text = text; },
  setQuadrant2Text: function(text) { this.quadrant2Text = text; },
  setQuadrant3Text: function(text) { this.quadrant3Text = text; },
  setQuadrant4Text: function(text) { this.quadrant4Text = text; },
  addPoint: function(id, x, y) { this.points.push({id, x, y}); },
  
  clear: function() {
    this.xAxisLeftText = '';
    this.xAxisRightText = '';
    this.yAxisTopText = '';
    this.yAxisBottomText = '';
    this.quadrant1Text = '';
    this.quadrant2Text = '';
    this.quadrant3Text = '';
    this.quadrant4Text = '';
    this.points = [];
  }
};

// Requirement Diagram Functions
const requirementFunctions = {
  // Enum objects as per original Mermaid implementation
  RequirementType: {
    REQUIREMENT: 'Requirement',
    FUNCTIONAL_REQUIREMENT: 'Functional Requirement',
    INTERFACE_REQUIREMENT: 'Interface Requirement',
    PERFORMANCE_REQUIREMENT: 'Performance Requirement',
    PHYSICAL_REQUIREMENT: 'Physical Requirement',
    DESIGN_CONSTRAINT: 'Design Constraint',
  },
  
  RiskLevel: {
    LOW_RISK: 'Low',
    MED_RISK: 'Medium',
    HIGH_RISK: 'High',
  },
  
  VerifyType: {
    VERIFY_ANALYSIS: 'Analysis',
    VERIFY_DEMONSTRATION: 'Demonstration',
    VERIFY_INSPECTION: 'Inspection',
    VERIFY_TEST: 'Test',
  },
  
  Relationships: {
    CONTAINS: 'contains',
    COPIES: 'copies',
    DERIVES: 'derives',
    SATISFIES: 'satisfies',
    VERIFIES: 'verifies',
    REFINES: 'refines',
    TRACES: 'traces',
  },
  
  // Legacy aliases for backwards compatibility
  REQUIREMENT: {
    REQUIREMENT: 'Requirement',
    FUNCTIONAL_REQUIREMENT: 'Functional Requirement',
    INTERFACE_REQUIREMENT: 'Interface Requirement',
    PERFORMANCE_REQUIREMENT: 'Performance Requirement',
    PHYSICAL_REQUIREMENT: 'Physical Requirement',
    DESIGN_CONSTRAINT: 'Design Constraint',
  },
  
  relations: [],
  latestRequirement: {
    requirementId: '',
    text: '',
    risk: '',
    verifyMethod: '',
    name: '',
    type: '',
    cssStyles: [],
    classes: ['default'],
  },
  requirements: new Map(),
  latestElement: {
    name: '',
    type: '',
    docRef: '',
    cssStyles: [],
    classes: ['default'],
  },
  elements: new Map(),
  classes: new Map(),
  direction: 'TB',
  
  getDirection: function() {
    return this.direction;
  },
  
  setDirection: function(dir) {
    this.direction = dir;
  },
  
  resetLatestRequirement: function() {
    this.latestRequirement = {
      requirementId: '',
      text: '',
      risk: '',
      verifyMethod: '',
      name: '',
      type: '',
      cssStyles: [],
      classes: ['default'],
    };
  },
  
  resetLatestElement: function() {
    this.latestElement = {
      name: '',
      type: '',
      docRef: '',
      cssStyles: [],
      classes: ['default'],
    };
  },
  
  addRequirement: function(name, type) {
    if (!this.requirements.has(name)) {
      this.requirements.set(name, {
        name,
        type,
        requirementId: this.latestRequirement.requirementId,
        text: this.latestRequirement.text,
        risk: this.latestRequirement.risk,
        verifyMethod: this.latestRequirement.verifyMethod,
        cssStyles: [],
        classes: ['default'],
      });
    }
    this.resetLatestRequirement();
    return this.requirements.get(name);
  },
  
  getRequirements: function() {
    return this.requirements;
  },
  
  setNewReqId: function(id) {
    if (this.latestRequirement !== undefined) {
      this.latestRequirement.requirementId = id;
    }
  },
  
  setNewReqText: function(text) {
    if (this.latestRequirement !== undefined) {
      this.latestRequirement.text = text;
    }
  },
  
  setNewReqRisk: function(risk) {
    if (this.latestRequirement !== undefined) {
      this.latestRequirement.risk = risk;
    }
  },
  
  setNewReqVerifyMethod: function(verifyMethod) {
    if (this.latestRequirement !== undefined) {
      this.latestRequirement.verifyMethod = verifyMethod;
    }
  },
  
  addElement: function(name) {
    if (!this.elements.has(name)) {
      this.elements.set(name, {
        name,
        type: this.latestElement.type,
        docRef: this.latestElement.docRef,
        cssStyles: [],
        classes: ['default'],
      });
    }
    this.resetLatestElement();
    return this.elements.get(name);
  },
  
  getElements: function() {
    return this.elements;
  },
  
  setNewElementType: function(type) {
    if (this.latestElement !== undefined) {
      this.latestElement.type = type;
    }
  },
  
  setNewElementDocRef: function(docRef) {
    if (this.latestElement !== undefined) {
      this.latestElement.docRef = docRef;
    }
  },
  
  addRelationship: function(type, src, dst) {
    this.relations.push({
      type,
      src,
      dst,
    });
  },
  
  getRelationships: function() {
    return this.relations;
  },
  
  setCssStyle: function(ids, styles) {
    for (const id of ids) {
      const node = this.requirements.get(id) ?? this.elements.get(id);
      if (!styles || !node) {
        return;
      }
      for (const s of styles) {
        if (s.includes(',')) {
          node.cssStyles.push(...s.split(','));
        } else {
          node.cssStyles.push(s);
        }
      }
    }
  },
  
  setClass: function(ids, classNames) {
    for (const id of ids) {
      const node = this.requirements.get(id) ?? this.elements.get(id);
      if (node) {
        for (const _class of classNames) {
          node.classes.push(_class);
          const styles = this.classes.get(_class)?.styles;
          if (styles) {
            node.cssStyles.push(...styles);
          }
        }
      }
    }
  },
  
  defineClass: function(ids, style) {
    for (const id of ids) {
      let styleClass = this.classes.get(id);
      if (styleClass === undefined) {
        styleClass = { id, styles: [], textStyles: [] };
        this.classes.set(id, styleClass);
      }

      if (style) {
        style.forEach((s) => {
          if (/color/.exec(s)) {
            const newStyle = s.replace('fill', 'bgFill');
            styleClass.textStyles.push(newStyle);
          }
          styleClass.styles.push(s);
        });
      }

      this.requirements.forEach((value) => {
        if (value.classes.includes(id)) {
          value.cssStyles.push(...style.flatMap((s) => s.split(',')));
        }
      });
      this.elements.forEach((value) => {
        if (value.classes.includes(id)) {
          value.cssStyles.push(...style.flatMap((s) => s.split(',')));
        }
      });
    }
  },
  
  getClasses: function() {
    return this.classes;
  },
  
  clear: function() {
    this.relations = [];
    this.resetLatestRequirement();
    this.requirements = new Map();
    this.resetLatestElement();
    this.elements = new Map();
    this.classes = new Map();
    this.direction = 'TB';
  }
};

// Block Diagram Functions
const blockFunctions = {
  blockDatabase: new Map(),
  edgeList: [],
  edgeCount: new Map(),
  classes: new Map(),
  blocks: [],
  rootBlock: { id: 'root', type: 'composite', children: [], columns: -1 },
  
  getLogger: function() {
    return {
      debug: () => {},
      info: () => {},
      warn: () => {},
      error: () => {}
    };
  },
  
  addStyleClass: function(id, styleAttributes = '') {
    let foundClass = this.classes.get(id);
    if (!foundClass) {
      foundClass = { id: id, styles: [], textStyles: [] };
      this.classes.set(id, foundClass);
    }
    if (styleAttributes !== undefined && styleAttributes !== null) {
      const STYLECLASS_SEP = ',';
      const COLOR_KEYWORD = 'color';
      const FILL_KEYWORD = 'fill';
      const BG_FILL = 'bgFill';
      
      styleAttributes.split(STYLECLASS_SEP).forEach((attrib) => {
        const fixedAttrib = attrib.replace(/([^;]*);/, '$1').trim();
        
        if (RegExp(COLOR_KEYWORD).exec(attrib)) {
          const newStyle1 = fixedAttrib.replace(FILL_KEYWORD, BG_FILL);
          const newStyle2 = newStyle1.replace(COLOR_KEYWORD, FILL_KEYWORD);
          foundClass.textStyles.push(newStyle2);
        }
        foundClass.styles.push(fixedAttrib);
      });
    }
  },
  
  addStyle2Node: function(id, styles = '') {
    const foundBlock = this.blockDatabase.get(id);
    if (foundBlock && styles !== undefined && styles !== null) {
      foundBlock.styles = styles.split(',');
    }
  },
  
  setCssClass: function(itemIds, cssClassName) {
    itemIds.split(',').forEach((id) => {
      let foundBlock = this.blockDatabase.get(id);
      if (foundBlock === undefined) {
        const trimmedId = id.trim();
        foundBlock = { id: trimmedId, type: 'na', children: [] };
        this.blockDatabase.set(trimmedId, foundBlock);
      }
      if (!foundBlock.classes) {
        foundBlock.classes = [];
      }
      foundBlock.classes.push(cssClassName);
    });
  },
  
  typeStr2Type: function(typeStr) {
    switch (typeStr) {
      case '[]':
        return 'square';
      case '()':
        return 'round';
      case '(())':
        return 'circle';
      case '>]':
        return 'rect_left_inv_arrow';
      case '{}':
        return 'diamond';
      case '{{}}':
        return 'hexagon';
      case '([])':
        return 'stadium';
      case '[[]]':
        return 'subroutine';
      case '[()]':
        return 'cylinder';
      case '((()))':
        return 'doublecircle';
      case '[//]':
        return 'lean_right';
      case '[\\\\]':
        return 'lean_left';
      case '[/\\]':
        return 'trapezoid';
      case '[\\/]':
        return 'inv_trapezoid';
      case '<[]>':
        return 'block_arrow';
      default:
        return 'na';
    }
  },
  
  edgeTypeStr2Type: function(typeStr) {
    switch (typeStr) {
      case '==':
        return 'thick';
      default:
        return 'normal';
    }
  },
  
  edgeStrToEdgeData: function(typeStr) {
    switch (typeStr.replace(/^[\s-]+|[\s-]+$/g, '')) {
      case 'x':
        return 'arrow_cross';
      case 'o':
        return 'arrow_circle';
      case '>':
        return 'arrow_point';
      default:
        return '';
    }
  },
  
  generateId: function() {
    const cnt = (this._cnt || 0) + 1;
    this._cnt = cnt;
    return 'id-' + Math.random().toString(36).substr(2, 12) + '-' + cnt;
  },
  
  setHierarchy: function(block) {
    this.rootBlock.children = block;
    this.populateBlockDatabase(block, this.rootBlock);
    this.blocks = this.rootBlock.children;
  },
  
  populateBlockDatabase: function(_blockList, parent) {
    const blockList = _blockList.flat();
    const children = [];
    const columnSettingBlock = blockList.find((b) => b?.type === 'column-setting');
    // Column setting is available but not used in this simplified implementation
    columnSettingBlock?.columns;
    
    for (const block of blockList) {
      if (block.label) {
        // block.label would be sanitized here in original implementation
      }
      if (block.type === 'classDef') {
        this.addStyleClass(block.id, block.css);
        continue;
      }
      if (block.type === 'applyClass') {
        this.setCssClass(block.id, block?.styleClass ?? '');
        continue;
      }
      if (block.type === 'applyStyles') {
        if (block?.stylesStr) {
          this.addStyle2Node(block.id, block?.stylesStr);
        }
        continue;
      }
      if (block.type === 'column-setting') {
        parent.columns = block.columns ?? -1;
      } else if (block.type === 'edge') {
        const count = (this.edgeCount.get(block.id) ?? 0) + 1;
        this.edgeCount.set(block.id, count);
        block.id = count + '-' + block.id;
        this.edgeList.push(block);
      } else {
        if (!block.label) {
          if (block.type === 'composite') {
            block.label = '';
          } else {
            block.label = block.id;
          }
        }
        const existingBlock = this.blockDatabase.get(block.id);

        if (existingBlock === undefined) {
          this.blockDatabase.set(block.id, block);
        } else {
          if (block.type !== 'na') {
            existingBlock.type = block.type;
          }
          if (block.label !== block.id) {
            existingBlock.label = block.label;
          }
        }

        if (block.children) {
          this.populateBlockDatabase(block.children, block);
        }
        if (block.type === 'space') {
          const w = block.width ?? 1;
          for (let j = 0; j < w; j++) {
            const newBlock = { ...block };
            newBlock.id = newBlock.id + '-' + j;
            this.blockDatabase.set(newBlock.id, newBlock);
            children.push(newBlock);
          }
        } else if (existingBlock === undefined) {
          children.push(block);
        }
      }
    }
    parent.children = children;
  },
  
  getColumns: function(blockId) {
    const block = this.blockDatabase.get(blockId);
    if (!block) {
      return -1;
    }
    if (block.columns) {
      return block.columns;
    }
    if (!block.children) {
      return -1;
    }
    return block.children.length;
  },
  
  getBlocksFlat: function() {
    return [...this.blockDatabase.values()];
  },
  
  getBlocks: function() {
    return this.blocks || [];
  },
  
  getEdges: function() {
    return this.edgeList;
  },
  
  getBlock: function(id) {
    return this.blockDatabase.get(id);
  },
  
  setBlock: function(block) {
    this.blockDatabase.set(block.id, block);
  },
  
  getClasses: function() {
    return this.classes;
  },
  
  clear: function() {
    this.blockDatabase = new Map([['root', this.rootBlock]]);
    this.edgeList = [];
    this.edgeCount = new Map();
    this.classes = new Map();
    this.blocks = [];
    this.rootBlock = { id: 'root', type: 'composite', children: [], columns: -1 };
  }
};

module.exports = {
  c4Functions,
  quadrantFunctions,
  requirementFunctions,
  blockFunctions
};