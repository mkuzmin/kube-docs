alias:: AllocationResult

- AllocationResult contains attributes of an allocated resource.

- Properties
  heading:: true

  - `allocationTimestamp` (Time)
    - AllocationTimestamp stores the time when the resources were allocated. This field is not guaranteed to be set, in which case that time is unknown.
      
      This is an alpha field and requires enabling the DRADeviceBindingConditions and DRAResourceClaimDeviceStatus feature gate.

  - `devices` ([[DeviceAllocationResult]])
    - Devices is the result of allocating devices.

  - `nodeSelector` (NodeSelector)
    - NodeSelector defines where the allocated resources are available. If unset, they are available everywhere.

