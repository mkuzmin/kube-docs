alias:: PortworxVolumeSource

- PortworxVolumeSource represents a Portworx volume resource.

- Properties
  heading:: true

  - `fsType` (string)
    - fSType represents the filesystem type to mount Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs". Implicitly inferred to be "ext4" if unspecified.

  - `readOnly` (boolean)
    - readOnly defaults to false (read/write). ReadOnly here will force the ReadOnly setting in VolumeMounts.

  - `volumeID` (string), **required**
    - volumeID uniquely identifies a Portworx volume

