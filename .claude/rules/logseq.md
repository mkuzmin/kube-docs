# Logseq Output Format

This project generates Markdown files formatted for [Logseq](https://logseq.com), an outliner-based knowledge management tool.

## Output Location

Logseq database is in `docs/pages/`.
Generated files go to `docs/pages/generated/`.

## Pages Properties
- Use `heading:: true` to mark items as section headings:

```markdown
- Properties
  heading:: true
  - `apiVersion`
  - `kind`
```

## Page Name
 If page title contains `/` -replace by `___`. Shashes are not allowed in filenames.
