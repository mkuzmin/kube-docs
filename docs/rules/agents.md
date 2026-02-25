# Custom Agents

Agents are defined in `.claude/agents/`. See Claude Code docs for frontmatter reference.

## Principles

**Least privilege:** Restrict tools to what's needed.

```yaml
tools: Glob, Bash(yq:*)  # only yq, not all Bash
```

**Minimal output:** Subagent results consume parent context tokens.

```markdown
## Workflow
...
3. Report: "Done." (or errors if any). No statistics, no file lists.
```

**Delegate bulk work:** If an agent processes many files with LLM-generated content, its own context fills up. Delegate to sub-agents - each gets isolated context, returns only "Done".

```yaml
tools: Glob, Task  # can spawn sub-agents
```

```markdown
## Workflow
1. List files, split into batches of 20
2. Spawn sub-agent per batch (up to 5 parallel)
3. Report: "Done." (or errors)
```