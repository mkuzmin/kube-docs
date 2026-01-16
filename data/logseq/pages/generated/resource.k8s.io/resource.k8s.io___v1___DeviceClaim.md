alias:: DeviceClaim

- DeviceClaim defines how to request devices with a ResourceClaim.

- Properties
  heading:: true

  - `config` ([][[DeviceClaimConfiguration]])
    - This field holds configuration for multiple potential drivers which could satisfy requests in this claim. It is ignored while allocating the claim.

  - `constraints` ([][[DeviceConstraint]])
    - These constraints must be satisfied by the set of devices that get allocated for the claim.

  - `requests` ([][[DeviceRequest]])
    - Requests represent individual requests for distinct devices which must all be satisfied. If empty, nothing needs to be allocated.

