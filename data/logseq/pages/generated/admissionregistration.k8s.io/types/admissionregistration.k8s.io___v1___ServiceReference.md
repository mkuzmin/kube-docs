alias:: ServiceReference

- ServiceReference holds a reference to Service.legacy.k8s.io

- Properties
  heading:: true

  - `name` (string), **required**
    - `name` is the name of the service. Required

  - `namespace` (string), **required**
    - `namespace` is the namespace of the service. Required

  - `path` (string)
    - `path` is an optional URL path which will be sent in any request to this service.

  - `port` (integer)
    - If specified, the port on the service that hosting webhook. Default to 443 for backward compatibility. `port` should be a valid port number (1-65535, inclusive).

