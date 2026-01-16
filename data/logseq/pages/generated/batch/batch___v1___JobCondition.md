alias:: JobCondition

- JobCondition describes current state of a job.

- Properties
  heading:: true

  - `lastProbeTime` (Time)
    - Last time the condition was checked.

  - `lastTransitionTime` (Time)
    - Last time the condition transit from one status to another.

  - `message` (string)
    - Human readable message indicating details about last transition.

  - `reason` (string)
    - (brief) reason for the condition's last transition.

  - `status` (string), **required**
    - Status of the condition, one of True, False, Unknown.

  - `type` (string), **required**
    - Type of job condition, Complete or Failed.

