1. List directories under `data/types/`. Create a task for each.
2. Process tasks sequentially (not in parallel). Stop for user review if issues need manual attention.

## Task Instructions

For each task, launch a Task subagent (general-purpose, model: sonnet).

**CRITICAL: Subagents must ONLY use the exact bash commands below. Do NOT create scripts, loops, or any other bash commands.**

### Workflow

1. Count types: `ls data/types/<DIRECTORY> | wc -l`

2. **If <100 types:** process directly
   - Extract: `yq 'filename, .description.formatted, "==="' data/types/<DIRECTORY>/**/*.yaml`
   - Read the yq output directly and scan for issues (see categories below)
   - Use Edit tool to fix issues found
   - Report: "Done. Fixed N files." or list files needing manual review.

3. **If ≥100 types:** spawn subagents per batch
   - Spawn Task subagents (general-purpose, model: haiku) for letter ranges:
     - `[A-E]*` → subagent 1
     - `[F-N]*` → subagent 2
     - `[O-Z]*` → subagent 3
   - Each subagent extracts: `yq 'filename, .description.formatted, "==="' data/types/<DIRECTORY>/[A-E]*/*.yaml`
   - Each subagent scans output, fixes with Edit tool, reports count
   - Aggregate results: "Done. Fixed N files total."

## Issue Categories

### 1. Inline Bullet Lists (auto-fix)

Multiple list items concatenated on a single line.

**Detect:** Patterns like `- item1 - item2` or `1. step 2. step` within a single line.

**Fix:** Split into separate lines, indent under parent.

```
# BAD
- "Allow": allows concurrently - "Forbid": forbids concurrent runs - "Replace": cancels job

# GOOD
  - "Allow": allows concurrently
  - "Forbid": forbids concurrent runs
  - "Replace": cancels job
```

### 2. Newline Without Empty Line (auto-fix)

Single newline doesn't create a paragraph break in markdown. Indented continuation renders as code block.

**Detect:** Text continues on next line without empty line separation (and isn't a list).

**Fix:** Either join into single line, or add empty line between paragraphs.

```
# BAD - indented continuation becomes code block
Some text here
  continues on next line.

# GOOD - joined into single line
Some text here continues on next line.
```

### 3. Empty Line Before List (auto-fix)

Empty line between intro text and list breaks visual association.

**Detect:** Colon + empty line + list items.

**Fix:** Remove the empty line AND indent list items.

```
# BAD
Valid values are:

- value1

# GOOD
Valid values are:
  - value1
```

### 4. Unbalanced Punctuation (auto-fix)

Missing closing quotes, parentheses, or backticks.

**Detect:** Unpaired `"`, `(`, `` ` `` characters.

**Fix:** Add missing closing character at end.

```
# BAD
"What if User were not a member of any groups

# GOOD
"What if User were not a member of any groups"
```

