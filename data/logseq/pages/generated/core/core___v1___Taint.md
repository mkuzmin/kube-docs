alias:: Taint

- The node this Taint is attached to has the "effect" on any pod that does not tolerate the Taint.

- Properties
  heading:: true

  - `effect` (string), **required**
    - Required. The effect of the taint on pods that do not tolerate the taint. Valid effects are NoSchedule, PreferNoSchedule and NoExecute.

  - `key` (string), **required**
    - Required. The taint key to be applied to a node.

  - `timeAdded` (Time)
    - TimeAdded represents the time at which the taint was added.

  - `value` (string)
    - The taint value corresponding to the taint key.

