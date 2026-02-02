alias:: StatefulSet

- StatefulSet represents a set of pods with consistent identities. Identities are defined as:
    - Network: A single stable DNS and hostname.
    - Storage: As many VolumeClaims as requested.
  
  The StatefulSet guarantees that a given network identity will always map to the same storage identity.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[StatefulSetSpec]])
    - Spec defines the desired identities of pods in this set.

  - `status` ([[StatefulSetStatus]])
    - Status is the current status of Pods in this StatefulSet. This data may be out of date by some window of time.

