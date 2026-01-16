alias:: DeviceClaimConfiguration

- DeviceClaimConfiguration is used for configuration parameters in DeviceClaim.

- Properties
  heading:: true

  - `opaque` ([[OpaqueDeviceConfiguration]])
    - Opaque provides driver-specific configuration parameters.

  - `requests` ([]string)
    - Requests lists the names of requests where the configuration applies. If empty, it applies to all requests.
      
      References to subrequests must include the name of the main request and may include the subrequest using the format <main request>[/<subrequest>]. If just the main request is given, the configuration applies to all subrequests.

