alias:: LinuxContainerUser

- LinuxContainerUser represents user identity information in Linux containers

- Properties
  heading:: true

  - `gid` (integer), **required**
    - GID is the primary gid initially attached to the first process in the container

  - `supplementalGroups` ([]integer)
    - SupplementalGroups are the supplemental groups initially attached to the first process in the container

  - `uid` (integer), **required**
    - UID is the primary uid initially attached to the first process in the container

