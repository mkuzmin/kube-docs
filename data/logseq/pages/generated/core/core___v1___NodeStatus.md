alias:: NodeStatus

- NodeStatus is information about the current status of a node.

- Properties
  heading:: true

  - `addresses` ([][[NodeAddress]])
    - List of addresses reachable to the node. Queried from cloud provider, if available. More info: https://kubernetes.io/docs/reference/node/node-status/#addresses Note: This field is declared as mergeable, but the merge key is not sufficiently unique, which can cause data corruption when it is merged. Callers should instead use a full-replacement patch. See https://pr.k8s.io/79391 for an example. Consumers should assume that addresses can change during the lifetime of a Node. However, there are some exceptions where this may not be possible, such as Pods that inherit a Node's address in its own status or consumers of the downward API (status.hostIP).

  - `allocatable` (object)
    - Allocatable represents the resources of a node that are available for scheduling. Defaults to Capacity.

  - `capacity` (object)
    - Capacity represents the total resources of a node. More info: https://kubernetes.io/docs/reference/node/node-status/#capacity

  - `conditions` ([][[NodeCondition]])
    - Conditions is an array of current observed node conditions. More info: https://kubernetes.io/docs/reference/node/node-status/#condition

  - `config` ([[NodeConfigStatus]])
    - Status of the config assigned to the node via the dynamic Kubelet config feature.

  - `daemonEndpoints` ([[NodeDaemonEndpoints]])
    - Endpoints of daemons running on the Node.

  - `features` ([[NodeFeatures]])
    - Features describes the set of features implemented by the CRI implementation.

  - `images` ([][[ContainerImage]])
    - List of container images on this node

  - `nodeInfo` ([[NodeSystemInfo]])
    - Set of ids/uuids to uniquely identify the node. More info: https://kubernetes.io/docs/reference/node/node-status/#info

  - `phase` (string)
    - NodePhase is the recently observed lifecycle phase of the node. More info: https://kubernetes.io/docs/concepts/nodes/node/#phase The field is never populated, and now is deprecated.

  - `runtimeHandlers` ([][[NodeRuntimeHandler]])
    - The available runtime handlers.

  - `volumesAttached` ([][[AttachedVolume]])
    - List of volumes that are attached to the node.

  - `volumesInUse` ([]string)
    - List of attachable volumes in use (mounted) by the node.

