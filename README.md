# About PlatePal

coming soon™

# Local Setup

## Database

This backend project requires a postgres server running locally on port 5432 with an already existing database

* Name: `platepal`
* Username: `postgres`
* Password: your_password

Please make sure this is set up before starting your application. Your used password needs to be set as environment
variable `PG_PASSWORD` when starting the application. In case you want to use completely different credentials or a
different port/server-host, you can modify those in the application.yml local properties, but do not push these changes.

## Run Config

The following environment variables are required to start this application:

* `PG_PASSWORD`as mentioned above

A sample run configuration for IntelliJ IDEA can be found [here](.run/SamplePlatePalRun.run.xml).
