alias:: PersistentVolumeStatus

- PersistentVolumeStatus is the current status of a persistent volume.

- Properties
  heading:: true

  - `lastPhaseTransitionTime` (Time)
    - lastPhaseTransitionTime is the time the phase transitioned from one to another and automatically resets to current time everytime a volume phase transitions.

  - `message` (string)
    - message is a human-readable message indicating details about why the volume is in this state.

  - `phase` (string)
    - phase indicates if a volume is available, bound to a claim, or released by a claim. More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#phase

  - `reason` (string)
    - reason is a brief CamelCase string that describes any failure and is meant for machine parsing and tidy display in the CLI.

