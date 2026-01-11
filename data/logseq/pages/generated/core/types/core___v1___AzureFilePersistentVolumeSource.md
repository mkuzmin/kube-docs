alias:: AzureFilePersistentVolumeSource

- AzureFile represents an Azure File Service mount on the host and bind mount to the pod.

- Properties
  heading:: true

  - `readOnly` (boolean)
    - readOnly defaults to false (read/write). ReadOnly here will force the ReadOnly setting in VolumeMounts.

  - `secretName` (string), **required**
    - secretName is the name of secret that contains Azure Storage Account Name and Key

  - `secretNamespace` (string)
    - secretNamespace is the namespace of the secret that contains Azure Storage Account Name and Key default is the same as the Pod

  - `shareName` (string), **required**
    - shareName is the azure Share Name

