alias:: CustomResourceDefinition

- CustomResourceDefinition represents a resource that should be exposed on the API server.  Its name MUST be in the format <.spec.name>.<.spec.group>.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[CustomResourceDefinitionSpec]]), **required**
    - spec describes how the user wants the resources to appear

  - `status` ([[CustomResourceDefinitionStatus]])
    - status indicates the actual state of the CustomResourceDefinition

