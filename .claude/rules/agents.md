# Custom Agents

Agents are defined in `.claude/agents/`. See Claude Code docs for frontmatter reference.

## Principles

**Least privilege:** Restrict tools to what's needed.

```yaml
tools: Glob, Bash(yq:*)  # only yq, not all Bash
```

**Minimal output:** Subagent results consume main context tokens.

```markdown
## Workflow
...
3. Report: "Done." (or errors if any). No statistics, no file lists.
```