alias:: NFSVolumeSource

- Represents an NFS mount that lasts the lifetime of a pod. NFS volumes do not support ownership management or SELinux relabeling.

- Properties
  heading:: true

  - `path` (string), **required**
    - path that is exported by the NFS server. More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs

  - `readOnly` (boolean)
    - readOnly here will force the NFS export to be mounted with read-only permissions. Defaults to false. More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs

  - `server` (string), **required**
    - server is the hostname or IP address of the NFS server. More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs

