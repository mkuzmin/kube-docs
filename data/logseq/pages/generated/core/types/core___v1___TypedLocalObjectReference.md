alias:: TypedLocalObjectReference

- TypedLocalObjectReference contains enough information to let you locate the typed referenced object inside the same namespace.

- Properties
  heading:: true

  - `apiGroup` (string)
    - APIGroup is the group for the resource being referenced. If APIGroup is not specified, the specified Kind must be in the core API group. For any other third-party types, APIGroup is required.

  - `kind` (string), **required**

  - `name` (string), **required**
    - Name is the name of resource being referenced

