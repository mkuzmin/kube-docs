alias:: UserInfo

- UserInfo holds the information about the user needed to implement the user.Info interface.

- Properties
  heading:: true

  - `extra` (object)
    - Any additional information provided by the authenticator.

  - `groups` ([]string)
    - The names of groups this user is a part of.

  - `uid` (string)
    - A unique value that identifies this user across time. If this user is deleted and another user by the same name is added, they will have different UIDs.

  - `username` (string)
    - The name that uniquely identifies this user among all active users.

