FROM openjdk:17

RUN mkdir -p /app/pokemon

WORKDIR /app/pokemon

COPY build/libs/api-0.0.1-SNAPSHOT.jar /app/pokemon/api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/pokemon/api.jar"]