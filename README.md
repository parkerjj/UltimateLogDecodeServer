Java version Tencent Mars XLog Decoder for UltimateLog
============================

This project has deploy an application on Google App Engine.
Direct Link: http://www.baiduyaowan.com



##UltimateLog


# How to Deploy it on your own Server

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi) (at least 3.5)
* [Gradle](https://gradle.org/gradle-download/) (optional)
* [Google Cloud SDK](https://cloud.google.com/sdk/) (aka gcloud)

## Setup

• Download and initialize the [Cloud SDK](https://cloud.google.com/sdk/)

    gcloud init

* Create an App Engine app within the current Google Cloud Project

    gcloud app create

## Maven
### Running locally

    mvn appengine:run

To use vist: http://localhost:8080/

### Deploying

    mvn appengine:deploy

To use vist:  https://YOUR-PROJECT-ID.appspot.com

## Gradle
### Running locally

    gradle appengineRun

If you do not have gradle installed, you can run using `./gradlew appengineRun`.

To use vist: http://localhost:8080/

### Deploying

    gradle appengineDeploy

If you do not have gradle installed, you can deploy using `./gradlew appengineDeploy`.

To use vist:  https://YOUR-PROJECT-ID.appspot.com

## Testing

    mvn verify

 or

    gradle test

As you add / modify the source code (`src/main/java/...`) it's very useful to add [unit testing](https://cloud.google.com/appengine/docs/java/tools/localunittesting)
to (`src/main/test/...`).  The following resources are quite useful:

* [Junit4](http://junit.org/junit4/)
* [Mockito](http://mockito.org/)
* [Truth](http://google.github.io/truth/)
# -UltimateLogDecodeServer
