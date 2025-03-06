FROM amazoncorretto:21-alpine-jdk

LABEL authors="chris"

COPY build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]