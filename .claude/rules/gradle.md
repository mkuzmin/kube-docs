## Build Commands

```bash
./gradlew build          # Build the project
./gradlew :app:run       # Run the application
./gradlew check          # Run all checks
./gradlew clean          # Clean build outputs
```

### Modules

- **app** - Main application module with entry point at `app.MainKt`
- **build-logic** - Convention plugins for shared build configuration

### Build Configuration

- **Version catalog**: `libs.versions.toml` defines all dependency versions
- **Convention plugins** in `build-logic/src/main/kotlin/`:
    - `buildlogic.kotlin.gradle.kts` - Kotlin JVM setup with progressive mode and extra warnings

### Source Layout

Modules use a flat source structure:
- Main sources: `src/` (not `src/main/kotlin/`)
- Test sources: `test/` (not `src/test/kotlin/`)
