alias:: MutatingWebhookConfiguration

- MutatingWebhookConfiguration describes the configuration of and admission webhook that accept or reject and may change the object.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `webhooks` ([][[MutatingWebhook]])
    - Webhooks is a list of webhooks and the affected resources and operations.

