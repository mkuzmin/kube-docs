alias:: VolumeAttachment

- VolumeAttachment captures the intent to attach or detach the specified volume to/from the specified node.
  
  VolumeAttachment objects are non-namespaced.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[VolumeAttachmentSpec]]), **required**
    - spec represents specification of the desired attach/detach volume behavior. Populated by the Kubernetes system.

  - `status` ([[VolumeAttachmentStatus]])
    - status represents status of the VolumeAttachment request. Populated by the entity completing the attach or detach operation, i.e. the external-attacher.

