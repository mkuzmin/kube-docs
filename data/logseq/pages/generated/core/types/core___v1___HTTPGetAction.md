alias:: HTTPGetAction

- HTTPGetAction describes an action based on HTTP Get requests.

- Properties
  heading:: true

  - `host` (string)
    - Host name to connect to, defaults to the pod IP. You probably want to set "Host" in httpHeaders instead.

  - `httpHeaders` ([][[HTTPHeader]])
    - Custom headers to set in the request. HTTP allows repeated headers.

  - `path` (string)
    - Path to access on the HTTP server.

  - `port` (IntOrString), **required**
    - Name or number of the port to access on the container. Number must be in the range 1 to 65535. Name must be an IANA_SVC_NAME.

  - `scheme` (string)
    - Scheme to use for connecting to the host. Defaults to HTTP.

