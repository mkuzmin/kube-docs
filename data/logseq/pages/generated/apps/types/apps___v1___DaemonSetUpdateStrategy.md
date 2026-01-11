alias:: DaemonSetUpdateStrategy

- DaemonSetUpdateStrategy is a struct used to control the update strategy for a DaemonSet.

- Properties
  heading:: true

  - `rollingUpdate` ([[RollingUpdateDaemonSet]])
    - Rolling update config params. Present only if type = "RollingUpdate".

  - `type` (string)
    - Type of daemon set update. Can be "RollingUpdate" or "OnDelete". Default is RollingUpdate.

