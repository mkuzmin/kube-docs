alias:: TokenRequest

- TokenRequest contains parameters of a service account token.

- Properties
  heading:: true

  - `audience` (string), **required**
    - audience is the intended audience of the token in "TokenRequestSpec". It will default to the audiences of kube apiserver.

  - `expirationSeconds` (integer)
    - expirationSeconds is the duration of validity of the token in "TokenRequestSpec". It has the same default value of "ExpirationSeconds" in "TokenRequestSpec".

