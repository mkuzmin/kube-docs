alias:: CronJobStatus

- CronJobStatus represents the current state of a cron job.

- Properties
  heading:: true

  - `active` ([]ObjectReference)
    - A list of pointers to currently running jobs.

  - `lastScheduleTime` (Time)
    - Information when was the last time the job was successfully scheduled.

  - `lastSuccessfulTime` (Time)
    - Information when was the last time the job successfully completed.

