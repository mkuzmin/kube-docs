alias:: ResourceClaimTemplate

- ResourceClaimTemplate is used to produce ResourceClaim objects.
  
  This is an alpha type and requires enabling the DynamicResourceAllocation feature gate.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[ResourceClaimTemplateSpec]]), **required**
    - Describes the ResourceClaim that is to be generated.
      
      This field is immutable. A ResourceClaim will get created by the control plane for a Pod when needed and then not get updated anymore.

