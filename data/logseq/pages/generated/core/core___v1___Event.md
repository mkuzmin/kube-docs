alias:: Event

- Event is a report of an event somewhere in the cluster.  Events have a limited retention time and triggers and messages may evolve with time.  Event consumers should not rely on the timing of an event with a given Reason reflecting a consistent underlying trigger, or the continued existence of events with that Reason.  Events should be treated as informative, best-effort, supplemental data.

- Properties
  heading:: true

  - `action` (string)
    - What action was taken/failed regarding to the Regarding object.

  - `apiVersion` (string), **required**

  - `count` (integer)
    - The number of times this event has occurred.

  - `eventTime` (MicroTime)
    - Time when this Event was first observed.

  - `firstTimestamp` (Time)
    - The time at which the event was first recorded. (Time of server receipt is in TypeMeta.)

  - `involvedObject` ([[ObjectReference]]), **required**
    - The object that this event is about.

  - `kind` (string), **required**

  - `lastTimestamp` (Time)
    - The time at which the most recent occurrence of this event was recorded.

  - `message` (string)
    - A human-readable description of the status of this operation.

  - `metadata` (ObjectMeta), **required**

  - `reason` (string)
    - This should be a short, machine understandable string that gives the reason for the transition into the object's current status.

  - `related` ([[ObjectReference]])
    - Optional secondary object for more complex actions.

  - `reportingComponent` (string)
    - Name of the controller that emitted this Event, e.g. `kubernetes.io/kubelet`.

  - `reportingInstance` (string)
    - ID of the controller instance, e.g. `kubelet-xyzf`.

  - `series` ([[EventSeries]])
    - Data about the Event series this event represents or nil if it's a singleton Event.

  - `source` ([[EventSource]])
    - The component reporting this event. Should be a short machine understandable string.

  - `type` (string)
    - Type of this event (Normal, Warning), new types could be added in the future

