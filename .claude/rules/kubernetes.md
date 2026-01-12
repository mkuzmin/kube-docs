# Kubernetes

Sources are in `external/kubernetes/` as a git submodule tracking `release-1.34` branch.

## OpenAPI Specs

- Source: `external/kubernetes/api/openapi-spec/v3/`
- Committed copy: `data/openapi/`

Use committed specs in `data/openapi/` for processing. Copy from submodule when updating K8s version.

See `openapi.md` for spec structure details.

## API Groups Mapping

`api-groups.csv` defines which specs to process:

| Column | Description |
|--------|-------------|
| group | API group name (e.g., `core`, `apps`, `batch`) |
| apiVersion | Version string (e.g., `v1`, `v1beta1`) |
| preferredVersion | `true` for the version we process |
| package | Package prefix (without version) - append version when filtering (e.g., `io.k8s.api.batch` + `.v1.`) |
| file | OpenAPI spec filename |

Note: Some groups in specs may not be enabled on all clusters (e.g., `internal.apiserver.k8s.io`, `storagemigration.k8s.io`). They are marked with `preferredVersion=false`.
