alias:: PodTemplate

- PodTemplate describes a template for creating copies of a predefined pod.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `template` ([[PodTemplateSpec]])
    - Template defines the pods that will be created from this pod template. https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

