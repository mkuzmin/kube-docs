alias:: DeploymentStrategy

- DeploymentStrategy describes how to replace existing pods with new ones.

- Properties
  heading:: true

  - `rollingUpdate` ([[RollingUpdateDeployment]])
    - Rolling update config params. Present only if DeploymentStrategyType = RollingUpdate.

  - `type` (string)
    - Type of deployment. Can be "Recreate" or "RollingUpdate". Default is RollingUpdate.

