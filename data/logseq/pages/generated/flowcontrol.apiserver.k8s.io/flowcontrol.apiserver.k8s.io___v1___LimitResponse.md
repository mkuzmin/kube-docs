alias:: LimitResponse

- LimitResponse defines how to handle requests that can not be executed right now.

- Properties
  heading:: true

  - `queuing` ([[QueuingConfiguration]])
    - `queuing` holds the configuration parameters for queuing. This field may be non-empty only if `type` is `"Queue"`.

  - `type` (string), **required**
    - `type` is "Queue" or "Reject". "Queue" means that requests that can not be executed upon arrival are held in a queue until they can be executed or a queuing limit is reached. "Reject" means that requests that can not be executed upon arrival are rejected. Required.

