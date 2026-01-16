alias:: EnvFromSource

- EnvFromSource represents the source of a set of ConfigMaps or Secrets

- Properties
  heading:: true

  - `configMapRef` ([[ConfigMapEnvSource]])
    - The ConfigMap to select from

  - `prefix` (string)
    - Optional text to prepend to the name of each environment variable. May consist of any printable ASCII characters except '='.

  - `secretRef` ([[SecretEnvSource]])
    - The Secret to select from

