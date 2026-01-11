alias:: ContainerExtendedResourceRequest

- ContainerExtendedResourceRequest has the mapping of container name, extended resource name to the device request name.

- Properties
  heading:: true

  - `containerName` (string), **required**
    - The name of the container requesting resources.

  - `requestName` (string), **required**
    - The name of the request in the special ResourceClaim which corresponds to the extended resource.

  - `resourceName` (string), **required**
    - The name of the extended resource in that container which gets backed by DRA.

