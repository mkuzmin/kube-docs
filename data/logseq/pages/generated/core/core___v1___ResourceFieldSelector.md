alias:: ResourceFieldSelector

- ResourceFieldSelector represents container resources (cpu, memory) and their output format

- Properties
  heading:: true

  - `containerName` (string)
    - Container name: required for volumes, optional for env vars

  - `divisor` (Quantity)
    - Specifies the output format of the exposed resources, defaults to "1"

  - `resource` (string), **required**
    - Required: resource to select

