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
kubernetes/               # Git submodule (specs in api/openapi-spec/v3/)
api-groups.csv            # Config: which API groups to process
specs/                    # Extracted descriptions (editable)
  {group}/{version}/{type}/
    _{type}.md            # Type description
    {field}.md            # Field descriptions
docs/pages/generated/     # Output: Logseq markdown
  {group}/                # One subdirectory per API group
    types/                # Helper types for that group
```

### Data Flow

**Extraction** (run once, or after upstream updates):
```
api-groups.csv → load API group configs
                 ↓
OpenAPI specs → filter schemas → extract descriptions → specs/
```

**Generation** (reads edited descriptions):
```
api-groups.csv → load API group configs
                 ↓
OpenAPI specs (structure only) + specs/ (descriptions) → generate markdown
                                                       → kinds go to {group}/
                                                       → helpers go to {group}/types/
```
