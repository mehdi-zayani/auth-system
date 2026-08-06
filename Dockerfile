# -----------------------------------------------------
# Docker image
# Description:
# Multi-stage build for local and cloud deployments
# (Railway, Render, etc.).
# The application is compiled inside the builder image,
# then only the executable JAR is copied into a minimal
# Java runtime image.
# -----------------------------------------------------

# -----------------------------------------------------
# Build stage
# -----------------------------------------------------
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# -----------------------------------------------------
# Runtime stage
# -----------------------------------------------------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]