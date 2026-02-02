alias:: NetworkPolicy

- NetworkPolicy describes what network traffic is allowed for a set of Pods

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[NetworkPolicySpec]])
    - spec represents the specification of the desired behavior for this NetworkPolicy.

