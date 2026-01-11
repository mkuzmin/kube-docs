alias:: ContainerState

- ContainerState holds a possible state of container. Only one of its members may be specified. If none of them is specified, the default one is ContainerStateWaiting.

- Properties
  heading:: true

  - `running` ([[ContainerStateRunning]])
    - Details about a running container

  - `terminated` ([[ContainerStateTerminated]])
    - Details about a terminated container

  - `waiting` ([[ContainerStateWaiting]])
    - Details about a waiting container

