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

## Data Classes

- Use `Map<String, JsonObject>` for strongly typed access while ignoring value details
- Use `= emptyMap()` default when JSON field may be missing
- Use `JsonArray? = null` or `JsonObject? = null` when only checking presence, not processing details
- Don't over-engineer - only add fields needed for the current task
