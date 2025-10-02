// Auto-generated AST type declarations
// Placeholder types for diagram AST structures

// Main diagram types
export interface Info {
  $type: 'Info';
  [key: string]: any;
}

export interface Packet {
  $type: 'Packet';
  [key: string]: any;
}

export interface PacketBlock {
  $type: 'PacketBlock';
  [key: string]: any;
}

export interface Pie {
  $type: 'Pie';
  [key: string]: any;
}

export interface PieSection {
  $type: 'PieSection';
  [key: string]: any;
}

export interface Architecture {
  $type: 'Architecture';
  [key: string]: any;
}

export interface GitGraph {
  $type: 'GitGraph';
  [key: string]: any;
}

export interface Radar {
  $type: 'Radar';
  [key: string]: any;
}

export interface Treemap {
  $type: 'Treemap';
  [key: string]: any;
}

export interface Branch {
  $type: 'Branch';
  [key: string]: any;
}

export interface Commit {
  $type: 'Commit';
  [key: string]: any;
}

export interface Merge {
  $type: 'Merge';
  [key: string]: any;
}

export interface Statement {
  $type: 'Statement';
  [key: string]: any;
}

// Type guards
export function isInfo(item: any): item is Info;
export function isPacket(item: any): item is Packet;
export function isPacketBlock(item: any): item is PacketBlock;
export function isPie(item: any): item is Pie;
export function isPieSection(item: any): item is PieSection;
export function isArchitecture(item: any): item is Architecture;
export function isGitGraph(item: any): item is GitGraph;
export function isTreemap(item: any): item is Treemap;
export function isBranch(item: any): item is Branch;
export function isCommit(item: any): item is Commit;
export function isMerge(item: any): item is Merge;

// Union type for all diagram AST types
export type MermaidAstType = Info | Packet | PacketBlock | Pie | PieSection | Architecture | GitGraph | Radar | Treemap | Branch | Commit | Merge | Statement;
