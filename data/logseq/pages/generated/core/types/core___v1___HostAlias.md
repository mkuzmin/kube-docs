alias:: HostAlias

- HostAlias holds the mapping between IP and hostnames that will be injected as an entry in the pod's hosts file.

- Properties
  heading:: true

  - `hostnames` ([]string)
    - Hostnames for the above IP address.

  - `ip` (string), **required**
    - IP address of the host file entry.

