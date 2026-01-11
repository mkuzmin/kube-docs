alias:: MetricValueStatus

- MetricValueStatus holds the current value for a metric

- Properties
  heading:: true

  - `averageUtilization` (integer)
    - currentAverageUtilization is the current value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods.

  - `averageValue` (Quantity)
    - averageValue is the current value of the average of the metric across all relevant pods (as a quantity)

  - `value` (Quantity)
    - value is the current value of the metric (as a quantity).

