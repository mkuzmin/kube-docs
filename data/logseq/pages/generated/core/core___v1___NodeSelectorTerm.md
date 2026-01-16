alias:: NodeSelectorTerm

- A null or empty node selector term matches no objects. The requirements of them are ANDed. The TopologySelectorTerm type implements a subset of the NodeSelectorTerm.

- Properties
  heading:: true

  - `matchExpressions` ([][[NodeSelectorRequirement]])
    - A list of node selector requirements by node's labels.

  - `matchFields` ([][[NodeSelectorRequirement]])
    - A list of node selector requirements by node's fields.

