alias:: ReplicationControllerStatus

- ReplicationControllerStatus represents the current status of a replication controller.

- Properties
  heading:: true

  - `availableReplicas` (integer)
    - The number of available replicas (ready for at least minReadySeconds) for this replication controller.

  - `conditions` ([][[ReplicationControllerCondition]])
    - Represents the latest available observations of a replication controller's current state.

  - `fullyLabeledReplicas` (integer)
    - The number of pods that have labels matching the labels of the pod template of the replication controller.

  - `observedGeneration` (integer)
    - ObservedGeneration reflects the generation of the most recently observed replication controller.

  - `readyReplicas` (integer)
    - The number of ready replicas for this replication controller.

  - `replicas` (integer), **required**
    - Replicas is the most recently observed number of replicas. More info: https://kubernetes.io/docs/concepts/workloads/controllers/replicationcontroller#what-is-a-replicationcontroller

