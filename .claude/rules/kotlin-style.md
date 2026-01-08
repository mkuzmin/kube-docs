# Kotlin Style

## Null Handling

- Avoid `!!` - use `?: error("message")` for explicit errors
- Use `.orEmpty()` for nullable collections

```kotlin
// bad
val schema = schemas["key"]!!

// good
val schema = schemas["key"] 
    ?: error("schema not found")
```

## Code Organization

- Extract constants as file-level `const val` (no `private` needed)
- File-level functions don't need `private`
- Create objects where they're used, not at file level

## Iteration & Strings

- Prefer `forEach` over `for` loops
- Use destructuring: `map.forEach { (key, _) -> }` over `map.keys.forEach`
- Use `buildString { appendLine() }` for multi-line strings
- Use `writeText(buildString { })` for file writing

```kotlin
outFile.writeText(buildString {
    items.forEach { appendLine("- $it") }
})
```

## Naming

- Prefer simple names: `generatePages` over `generateTypePages`
- Shadowing is fine

## Data Classes

- Use `= emptyMap()` default when JSON field may be missing
- Use `JsonArray? = null` or `JsonObject? = null` when only checking presence, not processing details
- Don't over-engineer - only add fields needed for the current task

## When Expressions

Use `when` for type dispatch over chained `?.let`:

```kotlin
fun format(prop: Property): String = when {
    prop.type == "array" -> "[]${format(prop.items)}"
    prop.type != null -> prop.type
    prop.allOf != null -> extractRef(prop.allOf)
    else -> error("unknown: $prop")
}
```

- End with `else -> error()` for exhaustive handling
- Include context in error messages

## Error Handling

Fail fast on unexpected data. Don't add silent fallbacks:

```kotlin
// bad - hides data problems
else -> "object"

// good - surfaces data problems immediately
else -> error("unknown property type: $prop")
```

If there's a problem with the dataset, fail with an error. Don't cover it with fallback values like `"object"` or `"array"`.

## Editing Discipline

Before each edit, ask: **"Is this change required by the task?"**

If no → don't touch it. This includes shadowed variables, formatting, naming.
