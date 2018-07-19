# sample application for beats & Elastic APM

## setup

Need to set some environment vars for this application
* DB settings
  * MYSQL_HOST
  * MYSQL_PORT
  * MYSQL_USER
  * MYSQL_PASSWORD

## build

* download and copy apm-agent.jar to `apm-jar` directory
* set apm-agent version to gradle.properties

### build docker image

To build image.

```
./gradlew docker
``` 

To push image to Docker Hub

```
./gradlew dockerPush
```
