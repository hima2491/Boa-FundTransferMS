FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/.my-fundtransfer-service-app.jar app.jar
EXPOSE 8080
# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
