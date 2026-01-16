alias:: ServiceReference

- ServiceReference holds a reference to Service.legacy.k8s.io

- Properties
  heading:: true

  - `name` (string)
    - Name is the name of the service

  - `namespace` (string)
    - Namespace is the namespace of the service

  - `port` (integer)
    - If specified, the port on the service that hosting webhook. Default to 443 for backward compatibility. `port` should be a valid port number (1-65535, inclusive).

