alias:: ObjectMetricSource

- ObjectMetricSource indicates how to scale on a metric describing a kubernetes object (for example, hits-per-second on an Ingress object).

- Properties
  heading:: true

  - `describedObject` ([[CrossVersionObjectReference]]), **required**
    - describedObject specifies the descriptions of a object,such as kind,name apiVersion

  - `metric` ([[MetricIdentifier]]), **required**
    - metric identifies the target metric by name and selector

  - `target` ([[MetricTarget]]), **required**
    - target specifies the target value for the given metric

