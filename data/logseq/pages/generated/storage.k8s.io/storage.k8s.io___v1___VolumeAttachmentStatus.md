alias:: VolumeAttachmentStatus

- VolumeAttachmentStatus is the status of a VolumeAttachment request.

- Properties
  heading:: true

  - `attachError` ([[VolumeError]])
    - attachError represents the last error encountered during attach operation, if any. This field must only be set by the entity completing the attach operation, i.e. the external-attacher.

  - `attached` (boolean), **required**
    - attached indicates the volume is successfully attached. This field must only be set by the entity completing the attach operation, i.e. the external-attacher.

  - `attachmentMetadata` (object)
    - attachmentMetadata is populated with any information returned by the attach operation, upon successful attach, that must be passed into subsequent WaitForAttach or Mount calls. This field must only be set by the entity completing the attach operation, i.e. the external-attacher.

  - `detachError` ([[VolumeError]])
    - detachError represents the last error encountered during detach operation, if any. This field must only be set by the entity completing the detach operation, i.e. the external-attacher.

