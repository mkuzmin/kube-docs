alias:: ContainerResizePolicy

- ContainerResizePolicy represents resource resize policy for the container.

- Properties
  heading:: true

  - `resourceName` (string), **required**
    - Name of the resource to which this resource resize policy applies. Supported values: cpu, memory.

  - `restartPolicy` (string), **required**
    - Restart policy to apply when specified resource is resized. If not specified, it defaults to NotRequired.

