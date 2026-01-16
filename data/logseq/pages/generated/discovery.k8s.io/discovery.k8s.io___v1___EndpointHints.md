alias:: EndpointHints

- EndpointHints provides hints describing how an endpoint should be consumed.

- Properties
  heading:: true

  - `forNodes` ([][[ForNode]])
    - forNodes indicates the node(s) this endpoint should be consumed by when using topology aware routing. May contain a maximum of 8 entries. This is an Alpha feature and is only used when the PreferSameTrafficDistribution feature gate is enabled.

  - `forZones` ([][[ForZone]])
    - forZones indicates the zone(s) this endpoint should be consumed by when using topology aware routing. May contain a maximum of 8 entries.

