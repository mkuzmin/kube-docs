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

- Extract constants like file paths as file-level `private const val`

## Iteration & Strings

- Prefer `forEach` over `for` loops
- Use `buildString { appendLine() }` for multi-line strings
- Use `writeText(buildString { })` for file writing

```kotlin
outFile.writeText(buildString {
    items.forEach { appendLine("- $it") }
})
```

## Data Classes

- Use `Map<String, JsonObject>` for strongly typed access while ignoring value details
- Use `= emptyMap()` default when JSON field may be missing
- Don't over-engineer - only add fields needed for the current task
