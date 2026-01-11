alias:: TokenReview

- TokenReview attempts to authenticate a token to a known user. Note: TokenReview requests may be cached by the webhook token authenticator plugin in the kube-apiserver.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[TokenReviewSpec]]), **required**
    - Spec holds information about the request being evaluated

  - `status` ([[TokenReviewStatus]])
    - Status is filled in by the server and indicates whether the request can be authenticated.

