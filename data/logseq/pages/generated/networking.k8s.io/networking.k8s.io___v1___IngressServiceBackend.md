alias:: IngressServiceBackend

- IngressServiceBackend references a Kubernetes Service as a Backend.

- Properties
  heading:: true

  - `name` (string), **required**
    - name is the referenced service. The service must exist in the same namespace as the Ingress object.

  - `port` ([[ServiceBackendPort]])
    - port of the referenced service. A port name or port number is required for a IngressServiceBackend.

