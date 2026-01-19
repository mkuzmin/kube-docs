# Tool Preferences

Prefer JetBrains MCP tools over generic tools:

| Instead of | Use |
|------------|-----|
| `Glob` | `mcp__jetbrains__find_files_by_name_keyword` or `find_files_by_glob` |
| `Grep` | `mcp__jetbrains__search_in_files_by_text` or `search_in_files_by_regex` |
| `ls` | `mcp__jetbrains__list_directory_tree` |
JetBrains tools are faster and have IDE context.

**Exception:** Use `Edit` for simple single-location changes. Use `replace_text_in_file` only for batch changes (renaming across file, `replaceAll: true`).

## Bulk File Processing

When processing many files (hundreds+), use subagents to keep the main context clean:

1. List files to process
2. Spawn Task (general-purpose) per batch of files
3. Launch 5-10 subagents in parallel
4. Check results for errors

**Efficiency:** Extract only needed data before passing to subagents. Use CLI tools like `yq` to extract fields rather than having subagents read entire files.

For multiple groups: use todo list, process one group at a time, stop for user review between groups.