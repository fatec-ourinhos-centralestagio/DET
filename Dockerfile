FROM amazoncorretto:11-alpine-jdk

WORKDIR /app

COPY build/libs/Det.jar /app/det.jar

EXPOSE 8080
EXPOSE 5432

CMD ["java", "-jar", "det.jar"]