In this conversation what have we learned about the project and code what is not documented in claude.md files and rules. Reflect.

Before editing, check existing rules - is it already covered? Would extending an example suffice?

Update `claude.md` files (including ones in subdirectories) and files inside `.claude/rules/`. Do not pollute core `claude.md` with implementation details - find better place.

Rules should be prescriptive (what TO do), not descriptive (what the code currently does).

Prefer usage examples over implementation details.

When corrected, improve - don't delete.

## Guidance

**Threshold** - Not all knowledge is worth documenting. Ask: would this prevent a mistake? Is it non-obvious? Minor additions can go into existing examples rather than new sections.

**Placement** - Match file purpose. Example: `logseq.md` documents Logseq syntax, not how we format Kubernetes fields for Logseq output.

**Minimal first** - Add to existing examples/sections before creating new ones. A field in an existing JSON example may be enough.

**Defend or improve** - When user pushes back, don't immediately revert. Either defend the change with reasoning, or find a way to improve it (make it smaller, move it, rephrase).
