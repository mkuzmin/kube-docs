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

## Early Returns in Lambdas

Use `return@forEach` for linear flow instead of nested conditionals:

```kotlin
// good - linear flow with early exit
items.forEach { item ->
    if (item.skip) return@forEach
    if (item.invalid) error("invalid item")

    process(item)
}

// avoid - nested when/let for simple cases
items.forEach { item ->
    when {
        item.skip -> {}
        item.invalid -> error("invalid item")
        else -> process(item)
    }
}
```

## Naming

- Prefer simple names: `generatePages` over `generateTypePages`
- Shadowing is acceptable but avoid when it reduces clarity

## Parameter Ordering

Order function parameters: source/input first, target/output last.

Pass computed values from caller rather than recomputing in callee:

```kotlin
// good - compute once, use twice
val grouped = items.groupBy { it.category }
printSummary(grouped.keys)
grouped.forEach { (category, items) -> process(category, items) }

// bad - same groupBy computed twice
printSummary(items)  // calls groupBy internally
items.groupBy { it.category }.forEach { ... }
```

Use the simplest type that satisfies the function's needs:

```kotlin
// good - caller extracts what callee needs
val grouped = items.groupBy { it.category }
printSummary(grouped.keys)  // pass Set<String>

// bad - callee extracts from rich objects
printSummary(items)  // pass List<Item>, extract keys inside
```

```kotlin
// good - source (schemas, group) before target (outDir)
fun generatePages(schemas: Map<String, Schema>, group: ApiGroup, outDir: File)

// good - both are inputs, no output parameter
fun loadSchemas(specDir: File, group: ApiGroup): Map<String, Schema>
```

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
