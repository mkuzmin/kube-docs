alias:: PreferredSchedulingTerm

- An empty preferred scheduling term matches all objects with implicit weight 0 (i.e. it's a no-op). A null preferred scheduling term matches no objects (i.e. is also a no-op).

- Properties
  heading:: true

  - `preference` ([[NodeSelectorTerm]]), **required**
    - A node selector term, associated with the corresponding weight.

  - `weight` (integer), **required**
    - Weight associated with matching the corresponding nodeSelectorTerm, in the range 1-100.

