alias:: LimitRange

- LimitRange sets resource usage limits for each kind of resource in a Namespace.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[LimitRangeSpec]])
    - Spec defines the limits enforced. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

