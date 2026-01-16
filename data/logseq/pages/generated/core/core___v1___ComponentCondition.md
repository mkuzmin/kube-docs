alias:: ComponentCondition

- Information about the condition of a component.

- Properties
  heading:: true

  - `error` (string)
    - Condition error code for a component. For example, a health check error code.

  - `message` (string)
    - Message about the condition for a component. For example, information about a health check.

  - `status` (string), **required**
    - Status of the condition for a component. Valid values for "Healthy": "True", "False", or "Unknown".

  - `type` (string), **required**
    - Type of condition for a component. Valid value: "Healthy"

