alias:: JobTemplateSpec

- JobTemplateSpec describes the data a Job should have when created from a template

- Properties
  heading:: true

  - `metadata` (ObjectMeta)

  - `spec` ([[JobSpec]])
    - Specification of the desired behavior of the job. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

