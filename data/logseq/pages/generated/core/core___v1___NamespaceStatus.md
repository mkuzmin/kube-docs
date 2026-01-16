alias:: NamespaceStatus

- NamespaceStatus is information about the current status of a Namespace.

- Properties
  heading:: true

  - `conditions` ([][[NamespaceCondition]])
    - Represents the latest available observations of a namespace's current state.

  - `phase` (string)
    - Phase is the current lifecycle phase of the namespace. More info: https://kubernetes.io/docs/tasks/administer-cluster/namespaces/

