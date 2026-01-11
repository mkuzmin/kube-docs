alias:: ScaleIOPersistentVolumeSource

- ScaleIOPersistentVolumeSource represents a persistent ScaleIO volume

- Properties
  heading:: true

  - `fsType` (string)
    - fsType is the filesystem type to mount. Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs", "ntfs". Default is "xfs"

  - `gateway` (string), **required**
    - gateway is the host address of the ScaleIO API Gateway.

  - `protectionDomain` (string)
    - protectionDomain is the name of the ScaleIO Protection Domain for the configured storage.

  - `readOnly` (boolean)
    - readOnly defaults to false (read/write). ReadOnly here will force the ReadOnly setting in VolumeMounts.

  - `secretRef` ([[SecretReference]]), **required**
    - secretRef references to the secret for ScaleIO user and other sensitive information. If this is not provided, Login operation will fail.

  - `sslEnabled` (boolean)
    - sslEnabled is the flag to enable/disable SSL communication with Gateway, default false

  - `storageMode` (string)
    - storageMode indicates whether the storage for a volume should be ThickProvisioned or ThinProvisioned. Default is ThinProvisioned.

  - `storagePool` (string)
    - storagePool is the ScaleIO Storage Pool associated with the protection domain.

  - `system` (string), **required**
    - system is the name of the storage system as configured in ScaleIO.

  - `volumeName` (string)
    - volumeName is the name of a volume already created in the ScaleIO system that is associated with this volume source.

