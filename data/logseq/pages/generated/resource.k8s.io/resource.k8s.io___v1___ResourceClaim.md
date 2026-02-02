alias:: ResourceClaim

- ResourceClaim describes a request for access to resources in the cluster, for use by workloads. For example, if a workload needs an accelerator device with specific properties, this is how that request is expressed. The status stanza tracks whether this claim has been satisfied and what specific resources have been allocated.
  
  This is an alpha type and requires enabling the DynamicResourceAllocation feature gate.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[ResourceClaimSpec]]), **required**
    - Spec describes what is being requested and how to configure it. The spec is immutable.

  - `status` ([[ResourceClaimStatus]])
    - Status describes whether the claim is ready to use and what has been allocated.

