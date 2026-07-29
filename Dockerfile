# -----------------------------------------------------
# Docker image
# Description:
# Builds a lightweight Docker image for running the
# Auth System Spring Boot application.
# -----------------------------------------------------

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]