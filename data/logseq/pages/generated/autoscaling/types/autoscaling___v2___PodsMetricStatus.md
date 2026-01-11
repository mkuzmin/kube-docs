alias:: PodsMetricStatus

- PodsMetricStatus indicates the current value of a metric describing each pod in the current scale target (for example, transactions-processed-per-second).

- Properties
  heading:: true

  - `current` ([[MetricValueStatus]]), **required**
    - current contains the current value for the given metric

  - `metric` ([[MetricIdentifier]]), **required**
    - metric identifies the target metric by name and selector

