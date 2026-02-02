alias:: ClusterRoleBinding

- ClusterRoleBinding references a ClusterRole, but not contain it.  It can reference a ClusterRole in the global namespace, and adds who information via Subject.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `roleRef` ([[RoleRef]]), **required**
    - RoleRef can only reference a ClusterRole in the global namespace. If the RoleRef cannot be resolved, the Authorizer must return an error. This field is immutable.

  - `subjects` ([][[Subject]])
    - Subjects holds references to the objects the role applies to.

