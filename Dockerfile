# Stage 1: Build the Spring Boot application
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml ./
# Copy the source code and static HTML files
COPY src ./src
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
