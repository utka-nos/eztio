FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} client-app.jar

ENTRYPOINT["java", "-jar", "client-app.jar"]