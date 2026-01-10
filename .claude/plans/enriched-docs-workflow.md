# Workflow for Managing Enriched Kubernetes Documentation

## Goal

Establish a workflow for maintaining enriched Kubernetes API documentation with:
- Single repository containing Kotlin code, data files, and Logseq database
- YAML format storing both original (from OpenAPI) and formatted (enriched) descriptions in same file
- Single-branch workflow: replace content on K8s version upgrades, git history tracks evolution
- AI-powered 3-way merge using Claude API to preserve enrichments during upgrades
- Everything version-controlled: OpenAPI specs, descriptions, generated Logseq pages

## Repository Structure

```
kube-docs/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── ExtractKt.kt          # Extract original from OpenAPI to YAML
│       ├── GenerateKt.kt         # Generate Logseq pages from formatted in YAML
│       └── MergeKt.kt            # AI 3-way merge for K8s upgrades
├── build-logic/
├── gradle/
├── external/                      # Git submodules (K8s source repos)
│   ├── kubernetes/                # github.com/kubernetes/kubernetes
│   │   ├── api/openapi-spec/v3/   # Source for OpenAPI specs
│   │   └── staging/src/k8s.io/    # Go sources (future use)
│   └── website/                   # github.com/kubernetes/website
│       └── content/en/docs/       # K8s documentation (future use)
├── data/
│   ├── openapi/                   # Committed K8s OpenAPI JSON files
│   │   ├── api__v1_openapi.json
│   │   ├── apis__apps__v1_openapi.json
│   │   └── ... (~20-30 files, ~15 MB total)
│   ├── docs/                      # YAML files with original + formatted descriptions
│   │   ├── core/v1/Pod/
│   │   │   ├── _Pod.yaml          # Type description
│   │   │   ├── spec.yaml          # Field description
│   │   │   └── status.yaml
│   │   ├── apps/v1/Deployment/
│   │   └── ...
│   └── logseq/                    # Full Logseq database
│       ├── logseq/
│       │   └── config.edn
│       ├── pages/
│       │   ├── generated/         # Kotlin-generated K8s docs
│       │   │   ├── API.md
│       │   │   ├── core___v1___Pod.md
│       │   │   └── ...
│       │   └── manual-notes.md    # Manual pages
│       └── journals/
├── api-groups.csv
└── README.md
```

### YAML File Format

Each description file contains both original and formatted text:

```yaml
original: |
  Pod is a collection of containers that can run on a host.
  This resource is created by clients and scheduled onto hosts.

formatted: |
  Pod is a collection of containers that can run on a host.
  This resource is created by clients and scheduled onto hosts.

  **Key Characteristics:**
  - Smallest deployable unit in Kubernetes
  - Shares network namespace (containers share IP address)
  - Shares storage volumes

  **See Also:**
  - [[Deployment]] - for managing replica Pods
  - [[StatefulSet]] - for stateful applications
```

**Key details:**
- `.yaml` extension (not `.yml`)
- `_Pod.yaml` naming for type descriptions (underscore prefix)
- `spec.yaml`, `status.yaml` for field descriptions (no prefix)
- `|` (pipe) syntax preserves all markdown formatting, no escaping needed
- No metadata fields initially (can add later)

### Git Workflow

- Single `main` branch (or current branch, no branching strategy)
- On K8s version upgrade: replace content, commit changes
- Git history shows evolution from version to version
- Tags mark stable points: `v1.34.0`, `v1.35.0`

## Gradle Tasks

Three main tasks for working with the documentation:

```bash
./gradlew :app:extract     # Extract original descriptions from OpenAPI to YAML files
./gradlew :app:generate    # Generate Logseq pages from formatted descriptions in YAML
./gradlew :app:merge       # AI-powered merge during K8s version upgrades
```

## Initial Setup (K8s 1.34)

### 1. Setup External Repositories

```bash
# Add Kubernetes repos as git submodules
git submodule add -b release-1.34 \
  https://github.com/kubernetes/kubernetes.git \
  external/kubernetes

git submodule add -b release-1.34 \
  https://github.com/kubernetes/website.git \
  external/website

git commit -m "Add Kubernetes source repos as submodules"
```

### 2. Copy OpenAPI Specs to Data Directory

```bash
# Create directory
mkdir -p data/openapi

# Copy specs from external/kubernetes
cp external/kubernetes/api/openapi-spec/v3/*.json data/openapi/

# Commit to version control
git add data/openapi/
git commit -m "Add K8s 1.34 OpenAPI specs"
```

### 3. Extract Original Descriptions to YAML

```bash
./gradlew :app:extract
# Creates data/docs/ with YAML files
# Each YAML has 'original' field populated, 'formatted' is copy of original

git add data/docs/
git commit -m "Extract original descriptions from K8s 1.34"
```

### 4. Enrich Formatted Descriptions

Edit YAML files in `data/docs/` to enhance the `formatted` field:
- Add markdown formatting (bold, lists, code blocks)
- Add cross-references using `[[TypeName]]` syntax
- Add code examples
- Add "Key Characteristics", "Common Patterns" sections
- Clarify technical terms

Example workflow:
```bash
# Edit data/docs/core/v1/Pod/_Pod.yaml
# Enhance the 'formatted' field while keeping 'original' unchanged

git add data/docs/core/v1/Pod/
git commit -m "Enrich Pod type descriptions"

# Continue with other types...
```

### 5. Generate Logseq Pages

```bash
./gradlew :app:generate
# Reads 'formatted' field from YAML files
# Generates markdown pages to data/logseq/pages/generated/

git add data/logseq/pages/generated/
git commit -m "Generate Logseq pages from formatted descriptions"
git tag v1.34.0
```

## Upgrading to New K8s Version (1.34 → 1.35)

### 1. Update External Kubernetes Repository

```bash
cd external/kubernetes
git fetch origin
git checkout release-1.35
cd ../..
git add external/kubernetes
git commit -m "Update kubernetes to release-1.35"
```

### 2. Update OpenAPI Specs

```bash
# Replace with new version's specs
rm data/openapi/*.json
cp external/kubernetes/api/openapi-spec/v3/*.json data/openapi/

git add data/openapi/
git commit -m "Update OpenAPI specs to K8s 1.35"
```

### 3. Extract New Original Descriptions

```bash
./gradlew :app:extract
# Overwrites 'original' field in data/docs/**/*.yaml
# 'formatted' field remains unchanged (still has 1.34 enrichments)
# Files NOT committed yet
```

### 4. Review Upstream Changes

```bash
git diff data/docs/
# Shows what changed in 'original' field between K8s 1.34 and 1.35
# Review to understand what K8s upstream modified
```

### 5. Run AI Merge

```bash
./gradlew :app:merge
# For each YAML file:
#   - Reads old 'original' from git (HEAD:data/docs/.../file.yaml)
#   - Reads new 'original' from working directory (just extracted)
#   - Reads current 'formatted' from working directory
#   - Uses Claude API to merge: preserve formatting enhancements while incorporating upstream changes
#   - Updates 'formatted' field in place
# Still NOT committed
```

### 6. Review Merge Results

```bash
git diff data/docs/
# Now shows changes to BOTH 'original' and 'formatted' fields
# Verify that:
#   - Upstream text changes are incorporated in 'formatted'
#   - Enrichments (examples, cross-refs, formatting) are preserved
#   - No regressions or lost content
```

### 7. Manual Corrections

```bash
# Edit any YAML files that need manual fixes
# Focus on merge conflicts or new types that need enrichment

git add data/docs/
git commit -m "Upgrade to K8s 1.35

- Updated original descriptions from upstream
- AI-merged formatted descriptions preserving enrichments
- Manual corrections: <list any manual changes>"
```

### 8. Regenerate Logseq Pages

```bash
./gradlew :app:generate

git add data/logseq/pages/generated/
git commit -m "Regenerate Logseq pages for K8s 1.35"
git tag v1.35.0
```

## AI Merge Implementation

**File**: `app/src/MergeKt.kt`

### Core Logic

The `:app:merge` Gradle task performs 3-way merge for each YAML file:

```kotlin
fun mergeFile(yamlFile: File) {
    // 1. Read current YAML from working directory
    val currentYaml = yamlFile.readText()
    val currentData = yaml.decodeFromString<DescriptionDoc>(currentYaml)

    // 2. Get old version from git
    val relativePath = yamlFile.relativeTo(File("data/docs"))
    val oldYaml = getFromGit("HEAD:data/docs/$relativePath")
    val oldData = yaml.decodeFromString<DescriptionDoc>(oldYaml)

    // 3. Check if original changed
    if (oldData.original == currentData.original) {
        // No upstream changes, keep formatted as-is
        return
    }

    // 4. Check if there are enrichments
    if (oldData.original == oldData.formatted) {
        // No enrichments, just copy new original to formatted
        currentData.formatted = currentData.original
        yamlFile.writeText(yaml.encodeToString(currentData))
        return
    }

    // 5. Use Claude API for intelligent merge
    val mergedFormatted = mergeWithClaude(
        oldOriginal = oldData.original,
        newOriginal = currentData.original,
        oldFormatted = oldData.formatted,
        filePath = relativePath.toString()
    )

    // 6. Update YAML file
    currentData.formatted = mergedFormatted
    yamlFile.writeText(yaml.encodeToString(currentData))
}

fun mergeWithClaude(oldOriginal: String, newOriginal: String, oldFormatted: String, filePath: String): String {
    val prompt = """
        Perform 3-way merge for Kubernetes API documentation.

        FILE: $filePath

        OLD ORIGINAL (K8s official description, previous version):
        $oldOriginal

        NEW ORIGINAL (K8s official description, current version):
        $newOriginal

        OLD FORMATTED (our enriched version with examples and formatting):
        $oldFormatted

        TASK:
        Generate NEW FORMATTED version that:
        1. Incorporates text changes from NEW ORIGINAL
        2. Preserves all enrichments from OLD FORMATTED (examples, cross-references, formatting, sections)
        3. Maintains high markdown quality
        4. If there's a conflict between upstream change and enrichment, favor the upstream text but preserve structure/formatting

        Return ONLY the merged markdown content, no explanation.
    """.trimIndent()

    val response = anthropic.messages.create(
        model = "claude-sonnet-4-5-20250929",
        maxTokens = 4096,
        messages = listOf(Message(role = "user", content = prompt))
    )

    return response.content.first().text
}

fun getFromGit(gitPath: String): String {
    val process = ProcessBuilder("git", "show", gitPath)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .start()
    return process.inputStream.bufferedReader().readText()
}
```

### Data Model

```kotlin
@Serializable
data class DescriptionDoc(
    val original: String,
    var formatted: String
)
```

## Critical Files to Create/Modify

### New Files:
1. `app/src/MergeKt.kt` - AI merge implementation
2. `data/openapi/` - Directory for committed OpenAPI specs
3. `data/docs/` - Directory for YAML description files
4. `data/logseq/` - Logseq database directory
5. `external/kubernetes/` - Git submodule (will be added)
6. `external/website/` - Git submodule (will be added)

### Modified Files:
1. `app/src/extract.kt` - Update to write YAML format instead of markdown
2. `app/src/generate.kt` - Update to read from YAML 'formatted' field
3. `app/build.gradle.kts` - Add Anthropic SDK and YAML serialization dependencies
4. `.gitignore` - Add any temporary files, but commit data/

## Dependencies to Add

```kotlin
// app/build.gradle.kts
dependencies {
    implementation("com.anthropic:anthropic-sdk-kotlin:0.x.x")  // Claude API
    implementation("com.charleskorn.kaml:kaml:0.x.x")           // YAML parsing
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.x.x")
}
```

## Verification Steps

### After Initial Setup (K8s 1.34):
1. Verify extraction: `find data/docs -name "*.yaml" | wc -l` should show ~2,214 files
2. Verify YAML format: `head -20 data/docs/core/v1/Pod/_Pod.yaml` should show original and formatted fields
3. Verify formatted initially equals original: `yq '.original == .formatted' data/docs/core/v1/Pod/_Pod.yaml` → true
4. After manual enrichment: `git diff data/docs/` should show only 'formatted' field changes
5. Verify generation: `ls data/logseq/pages/generated/ | wc -l` should show generated pages
6. Test in Logseq: Open `data/logseq/` in Logseq, verify pages render correctly

### After Upgrade (K8s 1.35):
1. After extract: `git diff data/docs/ | grep '^-original'` shows old upstream text
2. After merge: `git diff data/docs/ | grep '^-formatted'` shows preserved enrichments were updated
3. Spot-check 5-10 YAML files manually:
   - Verify 'original' reflects K8s 1.35 text
   - Verify 'formatted' has upstream changes + preserved enrichments
4. Test in Logseq: Verify pages render correctly with updated content

## Benefits

- **Single file review**: `git diff` shows both original and formatted changes together
- **Clear provenance**: YAML structure explicitly separates upstream vs enriched content
- **Easy upgrades**: AI handles merging, git history tracks everything
- **Extensible**: Can add metadata fields to YAML later
- **Familiar tooling**: Standard git workflow, Kotlin implementation
