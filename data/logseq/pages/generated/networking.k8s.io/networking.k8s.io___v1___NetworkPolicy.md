alias:: NetworkPolicy

- NetworkPolicy describes what network traffic is allowed for a set of Pods

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[NetworkPolicySpec]])
    - spec represents the specification of the desired behavior for this NetworkPolicy.

