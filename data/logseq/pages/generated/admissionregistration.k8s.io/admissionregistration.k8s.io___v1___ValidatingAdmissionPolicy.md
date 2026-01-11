alias:: ValidatingAdmissionPolicy

- ValidatingAdmissionPolicy describes the definition of an admission validation policy that accepts or rejects an object without changing it.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[ValidatingAdmissionPolicySpec]])
    - Specification of the desired behavior of the ValidatingAdmissionPolicy.

  - `status` ([[ValidatingAdmissionPolicyStatus]])
    - The status of the ValidatingAdmissionPolicy, including warnings that are useful to determine if the policy behaves in the expected way. Populated by the system. Read-only.

