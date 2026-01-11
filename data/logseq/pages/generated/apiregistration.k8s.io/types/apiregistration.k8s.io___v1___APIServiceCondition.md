alias:: APIServiceCondition

- APIServiceCondition describes the state of an APIService at a particular point

- Properties
  heading:: true

  - `lastTransitionTime` (Time)
    - Last time the condition transitioned from one status to another.

  - `message` (string)
    - Human-readable message indicating details about last transition.

  - `reason` (string)
    - Unique, one-word, CamelCase reason for the condition's last transition.

  - `status` (string), **required**
    - Status is the status of the condition. Can be True, False, Unknown.

  - `type` (string), **required**
    - Type is the type of the condition.

