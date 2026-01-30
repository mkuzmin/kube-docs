alias:: SubjectAccessReviewSpec

- SubjectAccessReviewSpec is a description of the access request.  Exactly one of ResourceAuthorizationAttributes and NonResourceAuthorizationAttributes must be set

- Properties
  heading:: true

  - `extra` (object)
    - Extra corresponds to the user.Info.GetExtra() method from the authenticator.  Since that is input to the authorizer it needs a reflection here.

  - `groups` ([]string)
    - Groups is the groups you're testing for.

  - `nonResourceAttributes` ([[NonResourceAttributes]])
    - NonResourceAttributes describes information for a non-resource access request

  - `resourceAttributes` ([[ResourceAttributes]])
    - ResourceAuthorizationAttributes describes information for a resource access request

  - `uid` (string)
    - UID information about the requesting user.

  - `user` (string)
    - User is the user you're testing for. If you specify "User" but not "Groups", then is it interpreted as "What if User were not a member of any groups"

