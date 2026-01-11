alias:: PriorityLevelConfigurationCondition

- PriorityLevelConfigurationCondition defines the condition of priority level.

- Properties
  heading:: true

  - `lastTransitionTime` (Time)
    - `lastTransitionTime` is the last time the condition transitioned from one status to another.

  - `message` (string)
    - `message` is a human-readable message indicating details about last transition.

  - `reason` (string)
    - `reason` is a unique, one-word, CamelCase reason for the condition's last transition.

  - `status` (string)
    - `status` is the status of the condition. Can be True, False, Unknown. Required.

  - `type` (string)
    - `type` is the type of the condition. Required.

