# Bulk File Processing

When processing many files (hundreds+), use subagents to keep the main context clean:

1. List files to process
2. Spawn Task (general-purpose) per batch of files
3. Launch 5-10 subagents in parallel
4. Check results for errors

**Efficiency:** Extract only needed data before passing to subagents. Use CLI tools like `yq` to extract fields rather than having subagents read entire files.

For multiple groups: use todo list, process one group at a time, stop for user review between groups.