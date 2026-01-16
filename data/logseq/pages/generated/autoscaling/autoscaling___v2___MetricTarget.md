alias:: MetricTarget

- MetricTarget defines the target value, average value, or average utilization of a specific metric

- Properties
  heading:: true

  - `averageUtilization` (integer)
    - averageUtilization is the target value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods. Currently only valid for Resource metric source type

  - `averageValue` (Quantity)
    - averageValue is the target value of the average of the metric across all relevant pods (as a quantity)

  - `type` (string), **required**
    - type represents whether the metric type is Utilization, Value, or AverageValue

  - `value` (Quantity)
    - value is the target value of the metric (as a quantity).

