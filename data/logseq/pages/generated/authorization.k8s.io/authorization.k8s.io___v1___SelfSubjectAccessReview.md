alias:: SelfSubjectAccessReview

- SelfSubjectAccessReview checks whether or the current user can perform an action.  Not filling in a spec.namespace means "in all namespaces".  Self is a special case, because users should always be able to check whether they can perform an action

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[SelfSubjectAccessReviewSpec]]), **required**
    - Spec holds information about the request being evaluated.  user and groups must be empty

  - `status` ([[SubjectAccessReviewStatus]])
    - Status is filled in by the server and indicates whether the request is allowed or not

