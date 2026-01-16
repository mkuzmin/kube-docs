alias:: CrossVersionObjectReference

- CrossVersionObjectReference contains enough information to let you identify the referred resource.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string), **required**

  - `name` (string), **required**
    - name is the name of the referent; More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names

