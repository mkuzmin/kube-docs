# Tool Preferences

Prefer JetBrains MCP tools over generic tools:

| Instead of | Use |
|------------|-----|
| `Glob` | `mcp__jetbrains__find_files_by_name_keyword` or `find_files_by_glob` |
| `Grep` | `mcp__jetbrains__search_in_files_by_text` or `search_in_files_by_regex` |
| `ls` | `mcp__jetbrains__list_directory_tree` |
JetBrains tools are faster and have IDE context.

**Exception:** Use `Edit` for simple single-location changes. Use `replace_text_in_file` only for batch changes (renaming across file, `replaceAll: true`).