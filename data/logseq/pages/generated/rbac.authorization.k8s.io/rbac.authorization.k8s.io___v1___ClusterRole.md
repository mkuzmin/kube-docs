alias:: ClusterRole

- ClusterRole is a cluster level, logical grouping of PolicyRules that can be referenced as a unit by a RoleBinding or ClusterRoleBinding.

- Properties
  heading:: true

  - `aggregationRule` ([[AggregationRule]])
    - AggregationRule is an optional field that describes how to build the Rules for this ClusterRole. If AggregationRule is set, then the Rules are controller managed and direct changes to Rules will be stomped by the controller.

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `rules` ([][[PolicyRule]])
    - Rules holds all the PolicyRules for this ClusterRole

