alias:: ResourceRequirements

- ResourceRequirements describes the compute resource requirements.

- Properties
  heading:: true

  - `claims` ([][[ResourceClaim]])
    - Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.
      
      This field depends on the DynamicResourceAllocation feature gate.
      
      This field is immutable. It can only be set for containers.

  - `limits` (object)
    - Limits describes the maximum amount of compute resources allowed. More info: https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/

  - `requests` (object)
    - Requests describes the minimum amount of compute resources required. If Requests is omitted for a container, it defaults to Limits if that is explicitly specified, otherwise to an implementation-defined value. Requests cannot exceed Limits. More info: https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/

