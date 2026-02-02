alias:: APIService

- APIService represents a server for a particular GroupVersion. Name must be "version.group".

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[APIServiceSpec]])
    - Spec contains information for locating and communicating with a server

  - `status` ([[APIServiceStatus]])
    - Status contains derived information about an API server

