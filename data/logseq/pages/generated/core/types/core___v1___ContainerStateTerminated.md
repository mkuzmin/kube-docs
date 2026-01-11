alias:: ContainerStateTerminated

- ContainerStateTerminated is a terminated state of a container.

- Properties
  heading:: true

  - `containerID` (string)
    - Container's ID in the format '<type>://<container_id>'

  - `exitCode` (integer), **required**
    - Exit status from the last termination of the container

  - `finishedAt` (Time)
    - Time at which the container last terminated

  - `message` (string)
    - Message regarding the last termination of the container

  - `reason` (string)
    - (brief) reason from the last termination of the container

  - `signal` (integer)
    - Signal from the last termination of the container

  - `startedAt` (Time)
    - Time at which previous execution of the container started

