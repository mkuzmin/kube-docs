alias:: ServiceStatus

- ServiceStatus represents the current status of a service.

- Properties
  heading:: true

  - `conditions` ([]Condition)
    - Current service state

  - `loadBalancer` ([[LoadBalancerStatus]])
    - LoadBalancer contains the current status of the load-balancer, if one is present.

