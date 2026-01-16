alias:: VsphereVirtualDiskVolumeSource

- Represents a vSphere volume resource.

- Properties
  heading:: true

  - `fsType` (string)
    - fsType is filesystem type to mount. Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified.

  - `storagePolicyID` (string)
    - storagePolicyID is the storage Policy Based Management (SPBM) profile ID associated with the StoragePolicyName.

  - `storagePolicyName` (string)
    - storagePolicyName is the storage Policy Based Management (SPBM) profile name.

  - `volumePath` (string), **required**
    - volumePath is the path that identifies vSphere volume vmdk

