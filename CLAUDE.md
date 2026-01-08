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
kubernetes/               # Git submodule (specs in api/openapi-spec/v3/)
api-groups.csv            # Config: which API groups to process
docs/pages/generated/     # Output: Logseq markdown
  types/                  # Subtypes (helper types)
```

### Data Flow

```
OpenAPI specs → load & filter schemas → generate markdown pages
                                      → kinds go to generated/
                                      → subtypes go to generated/types/
```
