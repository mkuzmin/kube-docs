alias:: LocalVolumeSource

- Local represents directly-attached storage with node affinity

- Properties
  heading:: true

  - `fsType` (string)
    - fsType is the filesystem type to mount. It applies only when the Path is a block device. Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs", "ntfs". The default value is to auto-select a filesystem if unspecified.

  - `path` (string), **required**
    - path of the full path to the volume on the node. It can be either a directory or block device (disk, partition, ...).

