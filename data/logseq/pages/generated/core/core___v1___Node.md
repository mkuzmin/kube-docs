alias:: Node

- Node is a worker node in Kubernetes. Each node will have a unique identifier in the cache (i.e. in etcd).

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[NodeSpec]])
    - Spec defines the behavior of a node. https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

  - `status` ([[NodeStatus]])
    - Most recently observed status of the node. Populated by the system. Read-only. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

