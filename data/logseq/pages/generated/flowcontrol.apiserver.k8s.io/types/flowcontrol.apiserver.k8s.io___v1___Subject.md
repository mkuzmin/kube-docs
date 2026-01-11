alias:: Subject

- Subject matches the originator of a request, as identified by the request authentication system. There are three ways of matching an originator; by user, group, or service account.

- Properties
  heading:: true

  - `group` ([[GroupSubject]])
    - `group` matches based on user group name.

  - `kind` (string), **required**

  - `serviceAccount` ([[ServiceAccountSubject]])
    - `serviceAccount` matches ServiceAccounts.

  - `user` ([[UserSubject]])
    - `user` matches based on username.

