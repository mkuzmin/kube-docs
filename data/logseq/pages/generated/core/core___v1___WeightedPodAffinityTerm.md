alias:: WeightedPodAffinityTerm

- The weights of all of the matched WeightedPodAffinityTerm fields are added per-node to find the most preferred node(s)

- Properties
  heading:: true

  - `podAffinityTerm` ([[PodAffinityTerm]]), **required**
    - Required. A pod affinity term, associated with the corresponding weight.

  - `weight` (integer), **required**
    - weight associated with matching the corresponding podAffinityTerm, in the range 1-100.

