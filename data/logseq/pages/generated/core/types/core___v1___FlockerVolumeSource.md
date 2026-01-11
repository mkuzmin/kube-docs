alias:: FlockerVolumeSource

- Represents a Flocker volume mounted by the Flocker agent. One and only one of datasetName and datasetUUID should be set. Flocker volumes do not support ownership management or SELinux relabeling.

- Properties
  heading:: true

  - `datasetName` (string)
    - datasetName is Name of the dataset stored as metadata -> name on the dataset for Flocker should be considered as deprecated

  - `datasetUUID` (string)
    - datasetUUID is the UUID of the dataset. This is unique identifier of a Flocker dataset

