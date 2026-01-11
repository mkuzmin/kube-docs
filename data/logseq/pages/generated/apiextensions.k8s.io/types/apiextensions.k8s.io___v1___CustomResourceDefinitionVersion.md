alias:: CustomResourceDefinitionVersion

- CustomResourceDefinitionVersion describes a version for CRD.

- Properties
  heading:: true

  - `additionalPrinterColumns` ([][[CustomResourceColumnDefinition]])
    - additionalPrinterColumns specifies additional columns returned in Table output. See https://kubernetes.io/docs/reference/using-api/api-concepts/#receiving-resources-as-tables for details. If no columns are specified, a single column displaying the age of the custom resource is used.

  - `deprecated` (boolean)
    - deprecated indicates this version of the custom resource API is deprecated. When set to true, API requests to this version receive a warning header in the server response. Defaults to false.

  - `deprecationWarning` (string)
    - deprecationWarning overrides the default warning returned to API clients. May only be set when `deprecated` is true. The default warning indicates this version is deprecated and recommends use of the newest served version of equal or greater stability, if one exists.

  - `name` (string), **required**
    - name is the version name, e.g. “v1”, “v2beta1”, etc. The custom resources are served under this version at `/apis/<group>/<version>/...` if `served` is true.

  - `schema` ([[CustomResourceValidation]])
    - schema describes the schema used for validation, pruning, and defaulting of this version of the custom resource.

  - `selectableFields` ([][[SelectableField]])
    - selectableFields specifies paths to fields that may be used as field selectors. A maximum of 8 selectable fields are allowed. See https://kubernetes.io/docs/concepts/overview/working-with-objects/field-selectors

  - `served` (boolean), **required**
    - served is a flag enabling/disabling this version from being served via REST APIs

  - `storage` (boolean), **required**
    - storage indicates this version should be used when persisting custom resources to storage. There must be exactly one version with storage=true.

  - `subresources` ([[CustomResourceSubresources]])
    - subresources specify what subresources this version of the defined custom resource have.

