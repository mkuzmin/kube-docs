alias:: AzureDiskVolumeSource

- AzureDisk represents an Azure Data Disk mount on the host and bind mount to the pod.

- Properties
  heading:: true

  - `cachingMode` (string)
    - cachingMode is the Host Caching mode: None, Read Only, Read Write.

  - `diskName` (string), **required**
    - diskName is the Name of the data disk in the blob storage

  - `diskURI` (string), **required**
    - diskURI is the URI of data disk in the blob storage

  - `fsType` (string)
    - fsType is Filesystem type to mount. Must be a filesystem type supported by the host operating system. Ex. "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified.

  - `kind` (string)

  - `readOnly` (boolean)
    - readOnly Defaults to false (read/write). ReadOnly here will force the ReadOnly setting in VolumeMounts.

