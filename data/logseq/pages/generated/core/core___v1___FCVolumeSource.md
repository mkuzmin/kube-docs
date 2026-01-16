alias:: FCVolumeSource

- Represents a Fibre Channel volume. Fibre Channel volumes can only be mounted as read/write once. Fibre Channel volumes support ownership management and SELinux relabeling.

- Properties
  heading:: true

  - `fsType` (string)
    - fsType is the filesystem type to mount. Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified.

  - `lun` (integer)
    - lun is Optional: FC target lun number

  - `readOnly` (boolean)
    - readOnly is Optional: Defaults to false (read/write). ReadOnly here will force the ReadOnly setting in VolumeMounts.

  - `targetWWNs` ([]string)
    - targetWWNs is Optional: FC target worldwide names (WWNs)

  - `wwids` ([]string)
    - wwids Optional: FC volume world wide identifiers (wwids) Either wwids or combination of targetWWNs and lun must be set, but not both simultaneously.

