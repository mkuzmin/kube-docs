alias:: PhotonPersistentDiskVolumeSource

- Represents a Photon Controller persistent disk resource.

- Properties
  heading:: true

  - `fsType` (string)
    - fsType is the filesystem type to mount. Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified.

  - `pdID` (string), **required**
    - pdID is the ID that identifies Photon Controller persistent disk

