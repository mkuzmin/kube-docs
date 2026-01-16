alias:: CSIPersistentVolumeSource

- Represents storage that is managed by an external CSI volume driver

- Properties
  heading:: true

  - `controllerExpandSecretRef` ([[SecretReference]])
    - controllerExpandSecretRef is a reference to the secret object containing sensitive information to pass to the CSI driver to complete the CSI ControllerExpandVolume call. This field is optional, and may be empty if no secret is required. If the secret object contains more than one secret, all secrets are passed.

  - `controllerPublishSecretRef` ([[SecretReference]])
    - controllerPublishSecretRef is a reference to the secret object containing sensitive information to pass to the CSI driver to complete the CSI ControllerPublishVolume and ControllerUnpublishVolume calls. This field is optional, and may be empty if no secret is required. If the secret object contains more than one secret, all secrets are passed.

  - `driver` (string), **required**
    - driver is the name of the driver to use for this volume. Required.

  - `fsType` (string)
    - fsType to mount. Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs", "ntfs".

  - `nodeExpandSecretRef` ([[SecretReference]])
    - nodeExpandSecretRef is a reference to the secret object containing sensitive information to pass to the CSI driver to complete the CSI NodeExpandVolume call. This field is optional, may be omitted if no secret is required. If the secret object contains more than one secret, all secrets are passed.

  - `nodePublishSecretRef` ([[SecretReference]])
    - nodePublishSecretRef is a reference to the secret object containing sensitive information to pass to the CSI driver to complete the CSI NodePublishVolume and NodeUnpublishVolume calls. This field is optional, and may be empty if no secret is required. If the secret object contains more than one secret, all secrets are passed.

  - `nodeStageSecretRef` ([[SecretReference]])
    - nodeStageSecretRef is a reference to the secret object containing sensitive information to pass to the CSI driver to complete the CSI NodeStageVolume and NodeStageVolume and NodeUnstageVolume calls. This field is optional, and may be empty if no secret is required. If the secret object contains more than one secret, all secrets are passed.

  - `readOnly` (boolean)
    - readOnly value to pass to ControllerPublishVolumeRequest. Defaults to false (read/write).

  - `volumeAttributes` (object)
    - volumeAttributes of the volume to publish.

  - `volumeHandle` (string), **required**
    - volumeHandle is the unique volume name returned by the CSI volume plugin’s CreateVolume to refer to the volume on all subsequent calls. Required.

