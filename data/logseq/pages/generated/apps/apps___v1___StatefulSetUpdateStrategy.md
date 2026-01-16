alias:: StatefulSetUpdateStrategy

- StatefulSetUpdateStrategy indicates the strategy that the StatefulSet controller will use to perform updates. It includes any additional parameters necessary to perform the update for the indicated strategy.

- Properties
  heading:: true

  - `rollingUpdate` ([[RollingUpdateStatefulSetStrategy]])
    - RollingUpdate is used to communicate parameters when Type is RollingUpdateStatefulSetStrategyType.

  - `type` (string)
    - Type indicates the type of the StatefulSetUpdateStrategy. Default is RollingUpdate.

