alias:: ObjectMetricStatus

- ObjectMetricStatus indicates the current value of a metric describing a kubernetes object (for example, hits-per-second on an Ingress object).

- Properties
  heading:: true

  - `current` ([[MetricValueStatus]]), **required**
    - current contains the current value for the given metric

  - `describedObject` ([[CrossVersionObjectReference]]), **required**
    - DescribedObject specifies the descriptions of a object,such as kind,name apiVersion

  - `metric` ([[MetricIdentifier]]), **required**
    - metric identifies the target metric by name and selector

