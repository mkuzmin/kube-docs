alias:: SelfSubjectReview

- SelfSubjectReview contains the user information that the kube-apiserver has about the user making this request. When using impersonation, users will receive the user info of the user being impersonated. If impersonation or request header authentication is used, any extra keys will have their case ignored and returned as lowercase.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `status` ([[SelfSubjectReviewStatus]])
    - Status is filled in by the server with the user attributes.

