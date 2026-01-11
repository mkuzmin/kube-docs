alias:: ResourceQuotaStatus

- ResourceQuotaStatus defines the enforced hard limits and observed use.

- Properties
  heading:: true

  - `hard` (object)
    - Hard is the set of enforced hard limits for each named resource. More info: https://kubernetes.io/docs/concepts/policy/resource-quotas/

  - `used` (object)
    - Used is the current observed total usage of the resource in the namespace.

