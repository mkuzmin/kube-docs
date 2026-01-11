alias:: GlusterfsVolumeSource

- Represents a Glusterfs mount that lasts the lifetime of a pod. Glusterfs volumes do not support ownership management or SELinux relabeling.

- Properties
  heading:: true

  - `endpoints` (string), **required**
    - endpoints is the endpoint name that details Glusterfs topology.

  - `path` (string), **required**
    - path is the Glusterfs volume path. More info: https://examples.k8s.io/volumes/glusterfs/README.md#create-a-pod

  - `readOnly` (boolean)
    - readOnly here will force the Glusterfs volume to be mounted with read-only permissions. Defaults to false. More info: https://examples.k8s.io/volumes/glusterfs/README.md#create-a-pod

