alias:: StorageClass

- StorageClass describes the parameters for a class of storage for which PersistentVolumes can be dynamically provisioned.
  
  StorageClasses are non-namespaced; the name of the storage class according to etcd is in ObjectMeta.Name.

- Properties
  heading:: true

  - `allowVolumeExpansion` (boolean)
    - allowVolumeExpansion shows whether the storage class allow volume expand.

  - `allowedTopologies` ([]TopologySelectorTerm)
    - allowedTopologies restrict the node topologies where volumes can be dynamically provisioned. Each volume plugin defines its own supported topology specifications. An empty TopologySelectorTerm list means there is no topology restriction. This field is only honored by servers that enable the VolumeScheduling feature.

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `mountOptions` ([]string)
    - mountOptions controls the mountOptions for dynamically provisioned PersistentVolumes of this storage class. e.g. ["ro", "soft"]. Not validated - mount of the PVs will simply fail if one is invalid.

  - `parameters` (object)
    - parameters holds the parameters for the provisioner that should create volumes of this storage class.

  - `provisioner` (string), **required**
    - provisioner indicates the type of the provisioner.

  - `reclaimPolicy` (string)
    - reclaimPolicy controls the reclaimPolicy for dynamically provisioned PersistentVolumes of this storage class. Defaults to Delete.

  - `volumeBindingMode` (string)
    - volumeBindingMode indicates how PersistentVolumeClaims should be provisioned and bound.  When unset, VolumeBindingImmediate is used. This field is only honored by servers that enable the VolumeScheduling feature.

