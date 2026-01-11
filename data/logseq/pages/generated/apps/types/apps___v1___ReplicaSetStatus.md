alias:: ReplicaSetStatus

- ReplicaSetStatus represents the current status of a ReplicaSet.

- Properties
  heading:: true

  - `availableReplicas` (integer)
    - The number of available non-terminating pods (ready for at least minReadySeconds) for this replica set.

  - `conditions` ([][[ReplicaSetCondition]])
    - Represents the latest available observations of a replica set's current state.

  - `fullyLabeledReplicas` (integer)
    - The number of non-terminating pods that have labels matching the labels of the pod template of the replicaset.

  - `observedGeneration` (integer)
    - ObservedGeneration reflects the generation of the most recently observed ReplicaSet.

  - `readyReplicas` (integer)
    - The number of non-terminating pods targeted by this ReplicaSet with a Ready Condition.

  - `replicas` (integer), **required**
    - Replicas is the most recently observed number of non-terminating pods. More info: https://kubernetes.io/docs/concepts/workloads/controllers/replicaset

  - `terminatingReplicas` (integer)
    - The number of terminating pods for this replica set. Terminating pods have a non-null .metadata.deletionTimestamp and have not yet reached the Failed or Succeeded .status.phase.
      
      This is an alpha field. Enable DeploymentReplicaSetTerminatingReplicas to be able to use this field.

