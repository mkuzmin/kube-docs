alias:: NodeSystemInfo

- NodeSystemInfo is a set of ids/uuids to uniquely identify the node.

- Properties
  heading:: true

  - `architecture` (string), **required**
    - The Architecture reported by the node

  - `bootID` (string), **required**
    - Boot ID reported by the node.

  - `containerRuntimeVersion` (string), **required**
    - ContainerRuntime Version reported by the node through runtime remote API (e.g. containerd://1.4.2).

  - `kernelVersion` (string), **required**
    - Kernel Version reported by the node from 'uname -r' (e.g. 3.16.0-0.bpo.4-amd64).

  - `kubeProxyVersion` (string), **required**
    - Deprecated: KubeProxy Version reported by the node.

  - `kubeletVersion` (string), **required**
    - Kubelet Version reported by the node.

  - `machineID` (string), **required**
    - MachineID reported by the node. For unique machine identification in the cluster this field is preferred. Learn more from man(5) machine-id: http://man7.org/linux/man-pages/man5/machine-id.5.html

  - `operatingSystem` (string), **required**
    - The Operating System reported by the node

  - `osImage` (string), **required**
    - OS Image reported by the node from /etc/os-release (e.g. Debian GNU/Linux 7 (wheezy)).

  - `swap` ([[NodeSwapStatus]])
    - Swap Info reported by the node.

  - `systemUUID` (string), **required**
    - SystemUUID reported by the node. For unique machine identification MachineID is preferred. This field is specific to Red Hat hosts https://access.redhat.com/documentation/en-us/red_hat_subscription_management/1/html/rhsm/uuid

