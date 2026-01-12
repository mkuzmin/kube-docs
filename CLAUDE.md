# CLAUDE.md
This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview
This project reads Kubernetes OpenAPI specs and renders them into a set of Markdown files suitable for [Logseq](https://logseq.com).

## Project Architecture
This is a Kotlin/JVM application using Gradle with a composite build structure.

### Key Technologies
- Kotlin 2.3
- Gradle 9.2
- JDK 25

### Project Structure

```
app/src/                  # Application code
  main.kt                 # Entry point: generate markdown
  extract.kt              # Entry point: extract descriptions
  yaml.kt                 # YAML serialization config and data classes
external/
  kubernetes/             # Git submodule (source for OpenAPI specs)
data/
  openapi/                # Committed OpenAPI JSON specs
  types/                  # YAML metadata files (editable)
    {group}/{TypeName}/
      _TypeName.yaml      # Type metadata
      fieldName.yaml      # Field metadata
  logseq/                 # Logseq database
    pages/generated/      # Generated markdown
      {group}/            # One subdirectory per API group
        types/            # Helper types for that group
api-groups.csv            # Config: which API groups to process
```

### Data Flow

**Extraction** (run once, or after upstream updates):
```
api-groups.csv + data/openapi/ → extract descriptions → data/types/
```

**Generation** (reads structure + edited descriptions):
```
data/openapi/ (structure) + data/types/ (descriptions) → data/logseq/pages/generated/
```
