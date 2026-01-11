alias:: FileKeySelector

- FileKeySelector selects a key of the env file.

- Properties
  heading:: true

  - `key` (string), **required**
    - The key within the env file. An invalid key will prevent the pod from starting. The keys defined within a source may consist of any printable ASCII characters except '='. During Alpha stage of the EnvFiles feature gate, the key size is limited to 128 characters.

  - `optional` (boolean)
    - Specify whether the file or its key must be defined. If the file or key does not exist, then the env var is not published. If optional is set to true and the specified key does not exist, the environment variable will not be set in the Pod's containers.
      
      If optional is set to false and the specified key does not exist, an error will be returned during Pod creation.

  - `path` (string), **required**
    - The path within the volume from which to select the file. Must be relative and may not contain the '..' path or start with '..'.

  - `volumeName` (string), **required**
    - The name of the volume mount containing the env file.

