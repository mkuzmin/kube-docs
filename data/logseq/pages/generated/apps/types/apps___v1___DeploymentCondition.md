alias:: DeploymentCondition

- DeploymentCondition describes the state of a deployment at a certain point.

- Properties
  heading:: true

  - `lastTransitionTime` (Time)
    - Last time the condition transitioned from one status to another.

  - `lastUpdateTime` (Time)
    - The last time this condition was updated.

  - `message` (string)
    - A human readable message indicating details about the transition.

  - `reason` (string)
    - The reason for the condition's last transition.

  - `status` (string), **required**
    - Status of the condition, one of True, False, Unknown.

  - `type` (string), **required**
    - Type of deployment condition.

