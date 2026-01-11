alias:: ExternalMetricStatus

- ExternalMetricStatus indicates the current value of a global metric not associated with any Kubernetes object.

- Properties
  heading:: true

  - `current` ([[MetricValueStatus]]), **required**
    - current contains the current value for the given metric

  - `metric` ([[MetricIdentifier]]), **required**
    - metric identifies the target metric by name and selector

