alias:: SecretKeySelector

- SecretKeySelector selects a key of a Secret.

- Properties
  heading:: true

  - `key` (string), **required**
    - The key of the secret to select from.  Must be a valid secret key.

  - `name` (string)
    - Name of the referent. This field is effectively required, but due to backwards compatibility is allowed to be empty. Instances of this type with an empty value here are almost certainly wrong. More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names

  - `optional` (boolean)
    - Specify whether the Secret or its key must be defined

