alias:: PersistentVolumeClaimTemplate

- PersistentVolumeClaimTemplate is used to produce PersistentVolumeClaim objects as part of an EphemeralVolumeSource.

- Properties
  heading:: true

  - `metadata` (ObjectMeta)

  - `spec` ([[PersistentVolumeClaimSpec]]), **required**
    - The specification for the PersistentVolumeClaim. The entire content is copied unchanged into the PVC that gets created from this template. The same fields as in a PersistentVolumeClaim are also valid here.

