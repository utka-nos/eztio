FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product-app.jar

ENTRYPOINT["java", "-jar", "product-app.jar"]