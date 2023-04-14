FROM openjdk:17-jdk-slim-buster
WORKDIR /opt/app
ARG JAR_FILE=target/platepal-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
