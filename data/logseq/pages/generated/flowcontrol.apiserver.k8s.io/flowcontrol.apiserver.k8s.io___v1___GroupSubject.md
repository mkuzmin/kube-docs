alias:: GroupSubject

- GroupSubject holds detailed information for group-kind subject.

- Properties
  heading:: true

  - `name` (string), **required**
    - name is the user group that matches, or "*" to match all user groups. See https://github.com/kubernetes/apiserver/blob/master/pkg/authentication/user/user.go for some well-known group names. Required.

