alias:: UncountedTerminatedPods

- UncountedTerminatedPods holds UIDs of Pods that have terminated but haven't been accounted in Job status counters.

- Properties
  heading:: true

  - `failed` ([]string)
    - failed holds UIDs of failed Pods.

  - `succeeded` ([]string)
    - succeeded holds UIDs of succeeded Pods.

