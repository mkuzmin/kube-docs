alias:: TCPSocketAction

- TCPSocketAction describes an action based on opening a socket

- Properties
  heading:: true

  - `host` (string)
    - Optional: Host name to connect to, defaults to the pod IP.

  - `port` (IntOrString), **required**
    - Number or name of the port to access on the container. Number must be in the range 1 to 65535. Name must be an IANA_SVC_NAME.

