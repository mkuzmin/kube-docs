alias:: ServiceReference

- ServiceReference holds a reference to Service.legacy.k8s.io

- Properties
  heading:: true

  - `name` (string), **required**
    - name is the name of the service. Required

  - `namespace` (string), **required**
    - namespace is the namespace of the service. Required

  - `path` (string)
    - path is an optional URL path at which the webhook will be contacted.

  - `port` (integer)
    - port is an optional service port at which the webhook will be contacted. `port` should be a valid port number (1-65535, inclusive). Defaults to 443 for backward compatibility.

