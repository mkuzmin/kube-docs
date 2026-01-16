alias:: ResourceClaimConsumerReference

- ResourceClaimConsumerReference contains enough information to let you locate the consumer of a ResourceClaim. The user must be a resource in the same namespace as the ResourceClaim.

- Properties
  heading:: true

  - `apiGroup` (string)
    - APIGroup is the group for the resource being referenced. It is empty for the core API. This matches the group in the APIVersion that is used when creating the resources.

  - `name` (string), **required**
    - Name is the name of resource being referenced.

  - `resource` (string), **required**
    - Resource is the type of resource being referenced, for example "pods".

  - `uid` (string), **required**
    - UID identifies exactly one incarnation of the resource.

