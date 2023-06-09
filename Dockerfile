FROM amazoncorretto:11-alpine-jdk

WORKDIR /app

COPY build/libs/Det.jar det.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "det.jar"]