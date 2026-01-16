alias:: ResourceAttributes

- ResourceAttributes includes the authorization attributes available for resource requests to the Authorizer interface

- Properties
  heading:: true

  - `fieldSelector` ([[FieldSelectorAttributes]])
    - fieldSelector describes the limitation on access based on field.  It can only limit access, not broaden it.

  - `group` (string)
    - Group is the API Group of the Resource.  "*" means all.

  - `labelSelector` ([[LabelSelectorAttributes]])
    - labelSelector describes the limitation on access based on labels.  It can only limit access, not broaden it.

  - `name` (string)
    - Name is the name of the resource being requested for a "get" or deleted for a "delete". "" (empty) means all.

  - `namespace` (string)
    - Namespace is the namespace of the action being requested.  Currently, there is no distinction between no namespace and all namespaces "" (empty) is defaulted for LocalSubjectAccessReviews "" (empty) is empty for cluster-scoped resources "" (empty) means "all" for namespace scoped resources from a SubjectAccessReview or SelfSubjectAccessReview

  - `resource` (string)
    - Resource is one of the existing resource types.  "*" means all.

  - `subresource` (string)
    - Subresource is one of the existing resource types.  "" means none.

  - `verb` (string)
    - Verb is a kubernetes resource API verb, like: get, list, watch, create, update, delete, proxy.  "*" means all.

  - `version` (string)
    - Version is the API Version of the Resource.  "*" means all.

