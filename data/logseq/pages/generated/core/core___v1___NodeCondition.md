alias:: NodeCondition

- NodeCondition contains condition information for a node.

- Properties
  heading:: true

  - `lastHeartbeatTime` (Time)
    - Last time we got an update on a given condition.

  - `lastTransitionTime` (Time)
    - Last time the condition transit from one status to another.

  - `message` (string)
    - Human readable message indicating details about last transition.

  - `reason` (string)
    - (brief) reason for the condition's last transition.

  - `status` (string), **required**
    - Status of the condition, one of True, False, Unknown.

  - `type` (string), **required**
    - Type of node condition.

