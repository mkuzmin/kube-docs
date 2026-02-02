alias:: ComponentStatus

- ComponentStatus (and ComponentStatusList) holds the cluster validation info. Deprecated: This API is deprecated in v1.19+

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `conditions` ([][[ComponentCondition]])
    - List of component conditions observed

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

