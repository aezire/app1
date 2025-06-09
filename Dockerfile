# Stage 1: Build the Spring Boot application
FROM maven:3.9.9-eclipse-temurin-11 AS builder
WORKDIR /app
COPY . .
# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
# Expose the default Spring Boot port
EXPOSE 8080
# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
