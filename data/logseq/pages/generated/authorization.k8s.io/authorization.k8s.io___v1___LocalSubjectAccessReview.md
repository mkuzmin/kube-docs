alias:: LocalSubjectAccessReview

- LocalSubjectAccessReview checks whether or not a user or group can perform an action in a given namespace. Having a namespace scoped resource makes it much easier to grant namespace scoped policy that includes permissions checking.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[SubjectAccessReviewSpec]]), **required**
    - Spec holds information about the request being evaluated.  spec.namespace must be equal to the namespace you made the request against.  If empty, it is defaulted.

  - `status` ([[SubjectAccessReviewStatus]])
    - Status is filled in by the server and indicates whether the request is allowed or not

