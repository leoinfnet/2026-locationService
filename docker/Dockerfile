FROM amazoncorretto:25-alpine3.23
EXPOSE 8080
WORKDIR /app
COPY app.jar .
ENTRYPOINT ["java", "-jar", "app.jar"]