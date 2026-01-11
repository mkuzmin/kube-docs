alias:: SubjectAccessReview

- SubjectAccessReview checks whether or not a user or group can perform an action.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[SubjectAccessReviewSpec]]), **required**
    - Spec holds information about the request being evaluated

  - `status` ([[SubjectAccessReviewStatus]])
    - Status is filled in by the server and indicates whether the request is allowed or not

