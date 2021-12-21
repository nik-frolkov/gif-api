# syntax=docker/dockerfile:1
FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ADD build/libs/gif-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]