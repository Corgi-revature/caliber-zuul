FROM openjdk:8-jre

ENTRYPOINT ["/usr/bin/java", "-jar", "/service.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /service.jar
