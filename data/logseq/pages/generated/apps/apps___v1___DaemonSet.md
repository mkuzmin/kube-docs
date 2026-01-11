alias:: DaemonSet

- DaemonSet represents the configuration of a daemon set.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[DaemonSetSpec]])
    - The desired behavior of this daemon set. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

  - `status` ([[DaemonSetStatus]])
    - The current status of this daemon set. This data may be out of date by some window of time. Populated by the system. Read-only. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

