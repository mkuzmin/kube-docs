alias:: HorizontalPodAutoscalerCondition

- HorizontalPodAutoscalerCondition describes the state of a HorizontalPodAutoscaler at a certain point.

- Properties
  heading:: true

  - `lastTransitionTime` (Time)
    - lastTransitionTime is the last time the condition transitioned from one status to another

  - `message` (string)
    - message is a human-readable explanation containing details about the transition

  - `reason` (string)
    - reason is the reason for the condition's last transition.

  - `status` (string), **required**
    - status is the status of the condition (True, False, Unknown)

  - `type` (string), **required**
    - type describes the current condition

