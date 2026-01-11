alias:: DaemonSetStatus

- DaemonSetStatus represents the current status of a daemon set.

- Properties
  heading:: true

  - `collisionCount` (integer)
    - Count of hash collisions for the DaemonSet. The DaemonSet controller uses this field as a collision avoidance mechanism when it needs to create the name for the newest ControllerRevision.

  - `conditions` ([][[DaemonSetCondition]])
    - Represents the latest available observations of a DaemonSet's current state.

  - `currentNumberScheduled` (integer), **required**
    - The number of nodes that are running at least 1 daemon pod and are supposed to run the daemon pod. More info: https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/

  - `desiredNumberScheduled` (integer), **required**
    - The total number of nodes that should be running the daemon pod (including nodes correctly running the daemon pod). More info: https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/

  - `numberAvailable` (integer)
    - The number of nodes that should be running the daemon pod and have one or more of the daemon pod running and available (ready for at least spec.minReadySeconds)

  - `numberMisscheduled` (integer), **required**
    - The number of nodes that are running the daemon pod, but are not supposed to run the daemon pod. More info: https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/

  - `numberReady` (integer), **required**
    - numberReady is the number of nodes that should be running the daemon pod and have one or more of the daemon pod running with a Ready Condition.

  - `numberUnavailable` (integer)
    - The number of nodes that should be running the daemon pod and have none of the daemon pod running and available (ready for at least spec.minReadySeconds)

  - `observedGeneration` (integer)
    - The most recent generation observed by the daemon set controller.

  - `updatedNumberScheduled` (integer)
    - The total number of nodes that are running updated daemon pod

