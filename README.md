# Auth System

A production-ready authentication and authorization system built with Spring Boot.

## Overview

Auth System is a modular monolith designed as a reusable foundation for modern backend applications.

The project follows clean software engineering principles and aims to provide a complete authentication and authorization solution that can later evolve into independent microservices if needed.

## Features

### Current

- JWT Authentication
- Role-Based Access Control (RBAC)
- User Management
- PostgreSQL
- Flyway Database Migration
- OpenAPI Documentation
- Docker Support

### Planned

- Refresh Tokens
- Email Verification
- Password Reset
- Multi-Factor Authentication
- OAuth2 Integration
- Audit Logs
- Rate Limiting

## Technology Stack

- Java 21
- Spring Boot 4
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker
- OpenAPI (Swagger)

## Project Structure

The project follows a feature-based modular monolith architecture.

Each business module owns its own controllers, services, repositories, entities and DTOs.

```
src/main/java
└── io/github/mehdizayani/authsystem
    ├── auth
    ├── user
    ├── role
    ├── common
    ├── configuration
    └── exception
```

## Getting Started

### Requirements

- Java 21
- Maven
- Docker

### Run PostgreSQL

```bash
docker compose up -d
```

### Run the application

```bash
./mvnw spring-boot:run
```

## Roadmap

- [x] Project initialization
- [ ] User Management
- [ ] Authentication
- [ ] Authorization
- [ ] Refresh Tokens
- [ ] Email Verification
- [ ] Password Reset
- [ ] OAuth2
- [ ] Multi-Factor Authentication

## License

This project is licensed under the MIT License.