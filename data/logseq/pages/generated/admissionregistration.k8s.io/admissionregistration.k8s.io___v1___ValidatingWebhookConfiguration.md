alias:: ValidatingWebhookConfiguration

- ValidatingWebhookConfiguration describes the configuration of and admission webhook that accept or reject and object without changing it.

- Properties
  heading:: true

  - `apiVersion` (string)

  - `kind` (string)

  - `metadata` (ObjectMeta)

  - `webhooks` ([][[ValidatingWebhook]])
    - Webhooks is a list of webhooks and the affected resources and operations.

