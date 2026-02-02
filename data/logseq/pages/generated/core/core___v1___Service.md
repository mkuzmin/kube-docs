alias:: Service

- Service is a named abstraction of software service (for example, mysql) consisting of local port (for example 3306) that the proxy listens on, and the selector that determines which pods will answer requests sent through the proxy.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[ServiceSpec]])
    - Spec defines the behavior of a service. https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

  - `status` ([[ServiceStatus]])
    - Most recently observed status of the service. Populated by the system. Read-only. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

