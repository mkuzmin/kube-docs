alias:: PodExtendedResourceClaimStatus

- PodExtendedResourceClaimStatus is stored in the PodStatus for the extended resource requests backed by DRA. It stores the generated name for the corresponding special ResourceClaim created by the scheduler.

- Properties
  heading:: true

  - `requestMappings` ([][[ContainerExtendedResourceRequest]]), **required**
    - RequestMappings identifies the mapping of <container, extended resource backed by DRA> to  device request in the generated ResourceClaim.

  - `resourceClaimName` (string), **required**
    - ResourceClaimName is the name of the ResourceClaim that was generated for the Pod in the namespace of the Pod.

