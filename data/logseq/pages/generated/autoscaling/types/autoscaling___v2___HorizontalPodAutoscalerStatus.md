alias:: HorizontalPodAutoscalerStatus

- HorizontalPodAutoscalerStatus describes the current status of a horizontal pod autoscaler.

- Properties
  heading:: true

  - `conditions` ([][[HorizontalPodAutoscalerCondition]])
    - conditions is the set of conditions required for this autoscaler to scale its target, and indicates whether or not those conditions are met.

  - `currentMetrics` ([][[MetricStatus]])
    - currentMetrics is the last read state of the metrics used by this autoscaler.

  - `currentReplicas` (integer)
    - currentReplicas is current number of replicas of pods managed by this autoscaler, as last seen by the autoscaler.

  - `desiredReplicas` (integer), **required**
    - desiredReplicas is the desired number of replicas of pods managed by this autoscaler, as last calculated by the autoscaler.

  - `lastScaleTime` (Time)
    - lastScaleTime is the last time the HorizontalPodAutoscaler scaled the number of pods, used by the autoscaler to control how often the number of pods is changed.

  - `observedGeneration` (integer)
    - observedGeneration is the most recent generation observed by this autoscaler.

