alias:: ISCSIVolumeSource

- Represents an ISCSI disk. ISCSI volumes can only be mounted as read/write once. ISCSI volumes support ownership management and SELinux relabeling.

- Properties
  heading:: true

  - `chapAuthDiscovery` (boolean)
    - chapAuthDiscovery defines whether support iSCSI Discovery CHAP authentication

  - `chapAuthSession` (boolean)
    - chapAuthSession defines whether support iSCSI Session CHAP authentication

  - `fsType` (string)
    - fsType is the filesystem type of the volume that you want to mount. Tip: Ensure that the filesystem type is supported by the host operating system. Examples: "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified. More info: https://kubernetes.io/docs/concepts/storage/volumes#iscsi

  - `initiatorName` (string)
    - initiatorName is the custom iSCSI Initiator Name. If initiatorName is specified with iscsiInterface simultaneously, new iSCSI interface <target portal>:<volume name> will be created for the connection.

  - `iqn` (string), **required**
    - iqn is the target iSCSI Qualified Name.

  - `iscsiInterface` (string)
    - iscsiInterface is the interface Name that uses an iSCSI transport. Defaults to 'default' (tcp).

  - `lun` (integer), **required**
    - lun represents iSCSI Target Lun number.

  - `portals` ([]string)
    - portals is the iSCSI Target Portal List. The portal is either an IP or ip_addr:port if the port is other than default (typically TCP ports 860 and 3260).

  - `readOnly` (boolean)
    - readOnly here will force the ReadOnly setting in VolumeMounts. Defaults to false.

  - `secretRef` ([[LocalObjectReference]])
    - secretRef is the CHAP Secret for iSCSI target and initiator authentication

  - `targetPortal` (string), **required**
    - targetPortal is iSCSI Target Portal. The Portal is either an IP or ip_addr:port if the port is other than default (typically TCP ports 860 and 3260).

