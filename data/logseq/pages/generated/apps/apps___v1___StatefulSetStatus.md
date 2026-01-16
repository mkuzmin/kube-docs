alias:: StatefulSetStatus

- StatefulSetStatus represents the current state of a StatefulSet.

- Properties
  heading:: true

  - `availableReplicas` (integer)
    - Total number of available pods (ready for at least minReadySeconds) targeted by this statefulset.

  - `collisionCount` (integer)
    - collisionCount is the count of hash collisions for the StatefulSet. The StatefulSet controller uses this field as a collision avoidance mechanism when it needs to create the name for the newest ControllerRevision.

  - `conditions` ([][[StatefulSetCondition]])
    - Represents the latest available observations of a statefulset's current state.

  - `currentReplicas` (integer)
    - currentReplicas is the number of Pods created by the StatefulSet controller from the StatefulSet version indicated by currentRevision.

  - `currentRevision` (string)
    - currentRevision, if not empty, indicates the version of the StatefulSet used to generate Pods in the sequence [0,currentReplicas).

  - `observedGeneration` (integer)
    - observedGeneration is the most recent generation observed for this StatefulSet. It corresponds to the StatefulSet's generation, which is updated on mutation by the API Server.

  - `readyReplicas` (integer)
    - readyReplicas is the number of pods created for this StatefulSet with a Ready Condition.

  - `replicas` (integer), **required**
    - replicas is the number of Pods created by the StatefulSet controller.

  - `updateRevision` (string)
    - updateRevision, if not empty, indicates the version of the StatefulSet used to generate Pods in the sequence [replicas-updatedReplicas,replicas)

  - `updatedReplicas` (integer)
    - updatedReplicas is the number of Pods created by the StatefulSet controller from the StatefulSet version indicated by updateRevision.

