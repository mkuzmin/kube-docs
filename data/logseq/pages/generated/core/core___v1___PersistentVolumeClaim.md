alias:: PersistentVolumeClaim

- PersistentVolumeClaim is a user's request for and claim to a persistent volume

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[PersistentVolumeClaimSpec]])
    - spec defines the desired characteristics of a volume requested by a pod author. More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#persistentvolumeclaims

  - `status` ([[PersistentVolumeClaimStatus]])
    - status represents the current information/status of a persistent volume claim. Read-only. More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#persistentvolumeclaims

