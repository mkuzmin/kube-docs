alias:: EndpointAddress

- EndpointAddress is a tuple that describes single IP address. Deprecated: This API is deprecated in v1.33+.

- Properties
  heading:: true

  - `hostname` (string)
    - The Hostname of this endpoint

  - `ip` (string), **required**
    - The IP of this endpoint. May not be loopback (127.0.0.0/8 or ::1), link-local (169.254.0.0/16 or fe80::/10), or link-local multicast (224.0.0.0/24 or ff02::/16).

  - `nodeName` (string)
    - Optional: Node hosting this endpoint. This can be used to determine endpoints local to a node.

  - `targetRef` ([[ObjectReference]])
    - Reference to object providing the endpoint.

