alias:: PriorityClass

- PriorityClass defines mapping from a priority class name to the priority integer value. The value can be any valid integer.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `description` (string)
    - description is an arbitrary string that usually provides guidelines on when this priority class should be used.

  - `globalDefault` (boolean)
    - globalDefault specifies whether this PriorityClass should be considered as the default priority for pods that do not have any priority class. Only one PriorityClass can be marked as `globalDefault`. However, if more than one PriorityClasses exists with their `globalDefault` field set to true, the smallest value of such global default PriorityClasses will be used as the default priority.

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `preemptionPolicy` (string)
    - preemptionPolicy is the Policy for preempting pods with lower priority. One of Never, PreemptLowerPriority. Defaults to PreemptLowerPriority if unset.

  - `value` (integer), **required**
    - value represents the integer value of this priority class. This is the actual priority that pods receive when they have the name of this class in their pod spec.

