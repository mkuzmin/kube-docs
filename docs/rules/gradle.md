## Build Commands

```bash
./gradlew build                      # Build the project
./gradlew :app:run                   # Generate markdown from data/types/
./gradlew :app:extractDescriptions   # Extract metadata from OpenAPI to data/types/
./gradlew check                      # Run all checks
./gradlew clean                      # Clean build outputs
```

### Modules

- **app** - Main application module with two entry points:
  - `app.MainKt` - generates markdown from data/types/
  - `app.ExtractKt` - extracts metadata to data/types/
- **build-logic** - Convention plugins for shared build configuration

### Build Configuration

- **Version catalog**: `libs.versions.toml` defines all dependency versions
- **Convention plugins** in `build-logic/src/main/kotlin/`:
    - `buildlogic.kotlin.gradle.kts` - Kotlin JVM setup with progressive mode and extra warnings
    - `buildlogic.kotlinx-serialization-json.gradle.kts` - Adds kotlinx.serialization JSON support

### Source Layout

Modules use a flat source structure:
- Main sources: `src/` (not `src/main/kotlin/`)
- Test sources: `test/` (not `src/test/kotlin/`)

### Working Directory

When running via `./gradlew :app:run`, working directory is `app/`. Use relative paths from there:
- `../data/openapi/...` for OpenAPI specs
- `../data/types/...` for YAML metadata
- `../data/logseq/...` for Logseq output
