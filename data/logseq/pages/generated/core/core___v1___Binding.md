alias:: Binding

- Binding ties one object to another; for example, a pod is bound to a node by a scheduler.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `target` ([[ObjectReference]]), **required**
    - The target object that you want to bind to the standard object.

