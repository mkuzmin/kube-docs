alias:: HTTPHeader

- HTTPHeader describes a custom header to be used in HTTP probes

- Properties
  heading:: true

  - `name` (string), **required**
    - The header field name. This will be canonicalized upon output, so case-variant names will be understood as the same header.

  - `value` (string), **required**
    - The header field value

