alias:: DeviceTaint

- The device this taint is attached to has the "effect" on any claim which does not tolerate the taint and, through the claim, to pods using the claim.

- Properties
  heading:: true

  - `effect` (string), **required**
    - The effect of the taint on claims that do not tolerate the taint and through such claims on the pods using them. Valid effects are NoSchedule and NoExecute. PreferNoSchedule as used for nodes is not valid here.

  - `key` (string), **required**
    - The taint key to be applied to a device. Must be a label name.

  - `timeAdded` (Time)
    - TimeAdded represents the time at which the taint was added. Added automatically during create or update if not set.

  - `value` (string)
    - The taint value corresponding to the taint key. Must be a label value.

