alias:: AggregationRule

- AggregationRule describes how to locate ClusterRoles to aggregate into the ClusterRole

- Properties
  heading:: true

  - `clusterRoleSelectors` ([]LabelSelector)
    - ClusterRoleSelectors holds a list of selectors which will be used to find ClusterRoles and create the rules. If any of the selectors match, then the ClusterRole's permissions will be added

