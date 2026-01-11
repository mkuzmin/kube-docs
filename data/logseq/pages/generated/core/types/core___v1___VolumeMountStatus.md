alias:: VolumeMountStatus

- VolumeMountStatus shows status of volume mounts.

- Properties
  heading:: true

  - `mountPath` (string), **required**
    - MountPath corresponds to the original VolumeMount.

  - `name` (string), **required**
    - Name corresponds to the name of the original VolumeMount.

  - `readOnly` (boolean)
    - ReadOnly corresponds to the original VolumeMount.

  - `recursiveReadOnly` (string)
    - RecursiveReadOnly must be set to Disabled, Enabled, or unspecified (for non-readonly mounts). An IfPossible value in the original VolumeMount must be translated to Disabled or Enabled, depending on the mount result.

