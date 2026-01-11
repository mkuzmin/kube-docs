alias:: NonResourceRule

- NonResourceRule holds information that describes a rule for the non-resource

- Properties
  heading:: true

  - `nonResourceURLs` ([]string)
    - NonResourceURLs is a set of partial urls that a user should have access to.  *s are allowed, but only as the full, final step in the path.  "*" means all.

  - `verbs` ([]string), **required**
    - Verb is a list of kubernetes non-resource API verbs, like: get, post, put, delete, patch, head, options.  "*" means all.

