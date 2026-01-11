alias:: ContainerRestartRule

- ContainerRestartRule describes how a container exit is handled.

- Properties
  heading:: true

  - `action` (string), **required**
    - Specifies the action taken on a container exit if the requirements are satisfied. The only possible value is "Restart" to restart the container.

  - `exitCodes` ([[ContainerRestartRuleOnExitCodes]])
    - Represents the exit codes to check on container exits.

