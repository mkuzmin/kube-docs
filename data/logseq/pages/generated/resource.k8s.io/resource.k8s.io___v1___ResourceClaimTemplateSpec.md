alias:: ResourceClaimTemplateSpec

- ResourceClaimTemplateSpec contains the metadata and fields for a ResourceClaim.

- Properties
  heading:: true

  - `metadata` (ObjectMeta)

  - `spec` ([[ResourceClaimSpec]]), **required**
    - Spec for the ResourceClaim. The entire content is copied unchanged into the ResourceClaim that gets created from this template. The same fields as in a ResourceClaim are also valid here.

