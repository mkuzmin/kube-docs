alias:: Role

- Role is a namespaced, logical grouping of PolicyRules that can be referenced as a unit by a RoleBinding.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `rules` ([][[PolicyRule]])
    - Rules holds all the PolicyRules for this Role

