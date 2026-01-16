alias:: EnvVarSource

- EnvVarSource represents a source for the value of an EnvVar.

- Properties
  heading:: true

  - `configMapKeyRef` ([[ConfigMapKeySelector]])
    - Selects a key of a ConfigMap.

  - `fieldRef` ([[ObjectFieldSelector]])
    - Selects a field of the pod: supports metadata.name, metadata.namespace, `metadata.labels['<KEY>']`, `metadata.annotations['<KEY>']`, spec.nodeName, spec.serviceAccountName, status.hostIP, status.podIP, status.podIPs.

  - `fileKeyRef` ([[FileKeySelector]])
    - FileKeyRef selects a key of the env file. Requires the EnvFiles feature gate to be enabled.

  - `resourceFieldRef` ([[ResourceFieldSelector]])
    - Selects a resource of the container: only resources limits and requests (limits.cpu, limits.memory, limits.ephemeral-storage, requests.cpu, requests.memory and requests.ephemeral-storage) are currently supported.

  - `secretKeyRef` ([[SecretKeySelector]])
    - Selects a key of a secret in the pod's namespace

