FROM openjdk:8-jdk-alpine
ADD . /CaliberZuul
WORKDIR /CaliberZuul
EXPOSE 9999
CMD ["java", "-Dspring.profiles.active=qa", -jar", "target/caliber-zuul.jar"]