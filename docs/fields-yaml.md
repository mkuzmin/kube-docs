# fields.yaml Reference

Source: `external/reference-docs/gen-resourcesdocs/config/v1.34/fields.yaml`

Used by Kubernetes API reference generator (submodule at `external/reference-docs/`) to group and order fields within complex types. Each Kubernetes version has its own config directory.

## Purpose

Without this config, fields display in OpenAPI spec order. The file provides:
- **Logical grouping** - related fields under headings (Containers, Scheduling, Lifecycle)
- **Curated ordering** - important fields first
- **Semantic organization** - separating Alpha, Beta, Deprecated fields

## Format

```yaml
- definition: io.k8s.api.core.v1.PodSpec    # Fully qualified type name
  field_categories:
  - name: Containers                        # Category heading (renders as ### in docs)
    fields:
    - containers                            # Field names in display order
    - initContainers
    - ephemeralContainers
  - name: Volumes
    fields:
    - volumes
  - fields:                                 # Category without name (no heading)
    - someField
```

## Relationship to Other Configs

| Config | Controls | Rendered As |
|--------|----------|-------------|
| `toc.yaml` | Which types appear on a page, section order | `## Section` |
| `fields.yaml` | Field grouping and order within a type | `### Category` |

Example for Pod page:
```
## PodSpec           ← from toc.yaml (otherDefinitions)
### Containers       ← from fields.yaml (field_categories.name)
- containers
- initContainers
### Volumes
- volumes
```

## Code References

- **Loading**: `external/reference-docs/gen-resourcesdocs/pkg/config/categories.go`
  - `LoadCategories()` - reads YAML files
  - `Categories.Find(key)` - looks up field categories by definition key
- **Rendering**: `external/reference-docs/gen-resourcesdocs/pkg/outputs/kwebsite/section.go`
  - `AddFieldCategory(name)` - starts new category
  - `AddProperty(...)` - adds field to current category
- **Templates**: `external/reference-docs/gen-resourcesdocs/templates/chapter.tmpl`
  ```go-template
  {{range .FieldCategories}}
  ### {{.Name}}
  {{range .Fields}}
  - {{.Name}}: ...
  {{end}}
  {{end}}
  ```

## Coverage

~45 complex types defined, primarily:
- Core: PodSpec, Container, Volume, ServiceSpec, PersistentVolumeSpec
- Apps: DeploymentSpec, StatefulSetSpec, DaemonSetSpec
- Batch: JobSpec, CronJobSpec
- Networking: NetworkPolicySpec
- RBAC: PolicyRule
- Meta: ObjectMeta

Types not in fields.yaml display fields in OpenAPI spec order without category groupings.
