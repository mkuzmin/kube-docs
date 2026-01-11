alias:: PriorityLevelConfiguration

- PriorityLevelConfiguration represents the configuration of a priority level.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[PriorityLevelConfigurationSpec]])
    - `spec` is the specification of the desired behavior of a "request-priority". More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

  - `status` ([[PriorityLevelConfigurationStatus]])
    - `status` is the current status of a "request-priority". More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

