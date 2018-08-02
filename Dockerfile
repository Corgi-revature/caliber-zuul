FROM openjdk:8-jre

CMD ["/wait.sh"]

ARG JAR_FILE

COPY target/${JAR_FILE} /service.jar
COPY scripts/wait.sh /wait.sh

