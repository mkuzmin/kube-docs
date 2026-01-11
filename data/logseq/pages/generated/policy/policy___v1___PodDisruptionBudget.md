alias:: PodDisruptionBudget

- PodDisruptionBudget is an object to define the max disruption that can be caused to a collection of pods

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `spec` ([[PodDisruptionBudgetSpec]])
    - Specification of the desired behavior of the PodDisruptionBudget.

  - `status` ([[PodDisruptionBudgetStatus]])
    - Most recently observed status of the PodDisruptionBudget.

