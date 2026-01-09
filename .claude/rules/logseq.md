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

## Page Naming

Use `___` as separator (slashes not allowed in filenames). Logseq automatically maps `___` to `/` in page names, so no alias is needed for this.

**Qualified names** for uniqueness across API groups:
- Filename: `{group}___{apiVersion}___{TypeName}.md`
- Example: `core___v1___Pod.md`
- Link as: `[[core/v1/Pod]]` (Logseq resolves `___` automatically)

**Aliases** for short references:
- Add `alias:: {TypeName}` at top of page
- Allows linking with `[[Pod]]` instead of full qualified name

```markdown
alias:: Pod

- Properties
  heading:: true
```

## Link Syntax

Use `[display text]([[page]])` for links with custom display text:

```markdown
- [v2]([[autoscaling/v2]])
```

Note: Obsidian-style `[[page|display]]` does NOT work in Logseq.

## Multiline Content

For multiline text in a single bullet, indent continuation lines to align with the text (after `- `):

```markdown
- First line of content
  Second line continues here
  Third line as well
```

Use `appendBlock(level, text)` to render multiline content:

```kotlin
appendBlock(level = 0, schema.description)  // top-level bullet
appendBlock(level = 2, prop.description)    // nested under property
```
