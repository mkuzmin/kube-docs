alias:: HPAScalingPolicy

- HPAScalingPolicy is a single policy which must hold true for a specified past interval.

- Properties
  heading:: true

  - `periodSeconds` (integer), **required**
    - periodSeconds specifies the window of time for which the policy should hold true. PeriodSeconds must be greater than zero and less than or equal to 1800 (30 min).

  - `type` (string), **required**
    - type is used to specify the scaling policy.

  - `value` (integer), **required**
    - value contains the amount of change which is permitted by the policy. It must be greater than zero

