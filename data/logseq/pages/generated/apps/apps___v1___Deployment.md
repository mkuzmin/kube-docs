alias:: Deployment

- Deployment enables declarative updates for Pods and ReplicaSets.

- Properties
  heading:: true

  - `apiVersion` (string), **required**

  - `kind` (string), **required**

  - `metadata` (ObjectMeta), **required**

  - `spec` ([[DeploymentSpec]])
    - Specification of the desired behavior of the Deployment.

  - `status` ([[DeploymentStatus]])
    - Most recently observed status of the Deployment.

