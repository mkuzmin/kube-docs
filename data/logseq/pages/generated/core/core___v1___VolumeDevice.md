alias:: VolumeDevice

- volumeDevice describes a mapping of a raw block device within a container.

- Properties
  heading:: true

  - `devicePath` (string), **required**
    - devicePath is the path inside of the container that the device will be mapped to.

  - `name` (string), **required**
    - name must match the name of a persistentVolumeClaim in the pod

