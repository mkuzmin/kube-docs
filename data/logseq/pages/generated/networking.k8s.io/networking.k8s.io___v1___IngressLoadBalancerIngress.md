alias:: IngressLoadBalancerIngress

- IngressLoadBalancerIngress represents the status of a load-balancer ingress point.

- Properties
  heading:: true

  - `hostname` (string)
    - hostname is set for load-balancer ingress points that are DNS based.

  - `ip` (string)
    - ip is set for load-balancer ingress points that are IP based.

  - `ports` ([][[IngressPortStatus]])
    - ports provides information about the ports exposed by this LoadBalancer.

