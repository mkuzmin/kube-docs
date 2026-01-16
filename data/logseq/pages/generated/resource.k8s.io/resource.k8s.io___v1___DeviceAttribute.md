alias:: DeviceAttribute

- DeviceAttribute must have exactly one field set.

- Properties
  heading:: true

  - `bool` (boolean)
    - BoolValue is a true/false value.

  - `int` (integer)
    - IntValue is a number.

  - `string` (string)
    - StringValue is a string. Must not be longer than 64 characters.

  - `version` (string)
    - VersionValue is a semantic version according to semver.org spec 2.0.0. Must not be longer than 64 characters.

