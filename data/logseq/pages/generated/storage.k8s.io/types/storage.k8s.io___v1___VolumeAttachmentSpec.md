alias:: VolumeAttachmentSpec

- VolumeAttachmentSpec is the specification of a VolumeAttachment request.

- Properties
  heading:: true

  - `attacher` (string), **required**
    - attacher indicates the name of the volume driver that MUST handle this request. This is the name returned by GetPluginName().

  - `nodeName` (string), **required**
    - nodeName represents the node that the volume should be attached to.

  - `source` ([[VolumeAttachmentSource]]), **required**
    - source represents the volume that should be attached.

