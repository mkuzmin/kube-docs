alias:: ValidatingAdmissionPolicyStatus

- ValidatingAdmissionPolicyStatus represents the status of an admission validation policy.

- Properties
  heading:: true

  - `conditions` ([]Condition)
    - The conditions represent the latest available observations of a policy's current state.

  - `observedGeneration` (integer)
    - The generation observed by the controller.

  - `typeChecking` ([[TypeChecking]])
    - The results of type checking for each expression. Presence of this field indicates the completion of the type checking.

