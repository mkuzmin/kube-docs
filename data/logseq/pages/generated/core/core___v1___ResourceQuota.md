alias:: ResourceQuota

- ResourceQuota sets aggregate quota restrictions enforced per namespace

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[ResourceQuotaSpec]])
    - Spec defines the desired quota. https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

  - `status` ([[ResourceQuotaStatus]])
    - Status defines the actual enforced quota and its current usage. https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status

