alias:: CustomResourceDefinitionCondition

- CustomResourceDefinitionCondition contains details for the current condition of this pod.

- Properties
  heading:: true

  - `lastTransitionTime` (Time)
    - lastTransitionTime last time the condition transitioned from one status to another.

  - `message` (string)
    - message is a human-readable message indicating details about last transition.

  - `reason` (string)
    - reason is a unique, one-word, CamelCase reason for the condition's last transition.

  - `status` (string), **required**
    - status is the status of the condition. Can be True, False, Unknown.

  - `type` (string), **required**
    - type is the type of the condition. Types include Established, NamesAccepted and Terminating.

