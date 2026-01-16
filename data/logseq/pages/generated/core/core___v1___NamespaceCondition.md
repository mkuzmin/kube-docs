alias:: NamespaceCondition

- NamespaceCondition contains details about state of namespace.

- Properties
  heading:: true

  - `lastTransitionTime` (Time)
    - Last time the condition transitioned from one status to another.

  - `message` (string)
    - Human-readable message indicating details about last transition.

  - `reason` (string)
    - Unique, one-word, CamelCase reason for the condition's last transition.

  - `status` (string), **required**
    - Status of the condition, one of True, False, Unknown.

  - `type` (string), **required**
    - Type of namespace controller condition.

