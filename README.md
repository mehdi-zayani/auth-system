# Auth System

[![Build](https://github.com/mehdi-zayani/auth-system/actions/workflows/ci.yml/badge.svg)](https://github.com/mehdi-zayani/auth-system/actions/workflows/ci.yaml)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.7-6DB33F)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169E1)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED)
![OpenAPI](https://img.shields.io/badge/OpenAPI-3.1-6BA539)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

Production-ready authentication and authorization API built with Spring Boot 3, Spring Security and JWT.

The project demonstrates how to implement a modern stateless authentication system following enterprise development practices. It includes user registration, JWT-based authentication, role-based authorization, request validation, centralized exception handling, database migrations with Flyway, OpenAPI documentation, automated testing, Docker support and a continuous integration pipeline.

The primary goal of this project is to serve as a clean, maintainable and production-oriented reference implementation for Spring Boot security.

---
## Features

* Stateless authentication using JSON Web Tokens (JWT)
* User registration and authentication endpoints
* Role-based authorization with Spring Security
* Method-level security using `@PreAuthorize`
* Password hashing with BCrypt
* Request validation with Jakarta Validation
* Centralized exception handling with consistent error responses
* Structured validation error reporting
* PostgreSQL persistence with Spring Data JPA
* Database versioning with Flyway
* OpenAPI 3 documentation with Swagger UI
* Spring Boot Actuator integration
* Docker and Docker Compose support
* Automated unit and integration tests with JUnit 5 and MockMvc
* GitHub Actions continuous integration workflow
* Clean project structure following Spring Boot best practices

---
## Technology Stack

| Category           | Technology                         |
| ------------------ | ---------------------------------- |
| Language           | Java 21                            |
| Framework          | Spring Boot 3                      |
| Security           | Spring Security, JWT               |
| Database           | PostgreSQL 17                      |
| ORM                | Spring Data JPA, Hibernate         |
| Database Migration | Flyway                             |
| Build Tool         | Maven                              |
| API Documentation  | OpenAPI 3, Swagger UI              |
| Monitoring         | Spring Boot Actuator               |
| Testing            | JUnit 5, Mockito, MockMvc, AssertJ |
| Containerization   | Docker, Docker Compose             |
| CI                 | GitHub Actions                     |

---
## Architecture

The application follows a layered architecture based on Spring Boot best practices.

```text
                HTTP Request
                     │
                     ▼
             Authentication Filter
                     │
                     ▼
               Spring Security
                     │
                     ▼
                REST Controller
                     │
                     ▼
                   Service
                     │
                     ▼
                Repository (JPA)
                     │
                     ▼
                 PostgreSQL
```

Authentication is handled using JSON Web Tokens (JWT). After a successful login, clients receive a signed access token that must be included in the `Authorization` header using the `Bearer` scheme for all protected endpoints.

Database schema management is performed with Flyway, ensuring versioned and repeatable database migrations across environments.

All unhandled exceptions are processed by centralized exception handlers, providing consistent and structured API error responses.

---
## Project Structure

```text
src
├── main
│   ├── java
│   │   └── io.github.mehdizayani.authsystem
│   │       ├── auth
│   │       ├── common
│   │       ├── configuration
│   │       ├── exception
│   │       ├── role
│   │       ├── security
│   │       ├── user
│   │       └── AuthSystemApplication.java
│   └── resources
│       ├── db
│       │   └── migration
│       └── application.yaml
└── test
    └── java
```

| Package         | Responsibility                                                     |
| --------------- | ------------------------------------------------------------------ |
| `auth`          | Authentication endpoints, requests, responses and business logic   |
| `common`        | Shared classes used across the application                         |
| `configuration` | Spring Boot, Security and OpenAPI configuration                    |
| `exception`     | Custom exceptions, global exception handlers and API error models  |
| `role`          | Role entity and repository                                         |
| `security`      | Spring Security configuration, JWT authentication and user details |
| `user`          | User entity, repository, controller and profile response           |
| `db/migration`  | Flyway database migration scripts                                  |
| `test`          | Unit and integration tests                                         |

The project is organized following a feature-oriented package structure while keeping a clear separation between presentation, business and persistence layers.

---
## Getting Started

### Prerequisites

Before running the application, make sure the following tools are installed:

* Java 21 or later
* Maven 3.9+
* Docker and Docker Compose (recommended)
* Git

### Clone the Repository

```bash
git clone https://github.com/mehdi-zayani/auth-system.git
cd auth-system
```

### Build the Project

Compile the application and execute the test suite:

```bash
./mvnw clean verify
```

If all tests pass successfully, the project is ready to be configured and executed.

---
## Configuration

The application uses environment variables for runtime configuration.

Create a local environment file from the provided template:

```bash
cp .env.example .env
```

Update the values according to your local environment before starting the application.

| Variable            | Description                                   | Default                                        |
| ------------------- | --------------------------------------------- | ---------------------------------------------- |
| `POSTGRES_DB`       | PostgreSQL database name                      | `auth_system`                                  |
| `POSTGRES_USER`     | PostgreSQL username                           | `postgres`                                     |
| `POSTGRES_PASSWORD` | PostgreSQL password                           | `postgres`                                     |
| `DB_URL`            | JDBC connection URL                           | `jdbc:postgresql://localhost:5432/auth_system` |
| `DB_USERNAME`       | Database username                             | `postgres`                                     |
| `DB_PASSWORD`       | Database password                             | `postgres`                                     |
| `JWT_SECRET_KEY`    | Base64-encoded secret used to sign JWT tokens | Required                                       |
| `JWT_EXPIRATION`    | JWT expiration time in milliseconds           | `3600000`                                      |
| `SERVER_PORT`       | Application HTTP port                         | `8080`                                         |

The application reads these values through Spring Boot's externalized configuration mechanism, allowing different environments (development, testing and production) to use different settings without modifying the source code.

**Important**

The `JWT_SECRET_KEY` must be a sufficiently long Base64-encoded secret. Never commit production secrets to the repository.

---
## Running the Application

### Using Docker Compose (Recommended)

Start the PostgreSQL database:

```bash
docker compose up -d
```

Then start the application:

```bash
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

### Running Without Docker

If you already have a local PostgreSQL instance running, update your environment variables accordingly and start the application with:

```bash
./mvnw spring-boot:run
```

### Building an Executable JAR

```bash
./mvnw clean package
```

Run the generated JAR:

```bash
java -jar target/auth-system-*.jar
```

---
## API Documentation

The project provides interactive API documentation through Swagger UI and operational endpoints through Spring Boot Actuator.

### Swagger UI

Once the application is running, the OpenAPI documentation is available at:

```text id="j9te58"
http://localhost:8080/swagger-ui/index.html
```

Swagger UI allows you to:

* Explore all available endpoints
* Inspect request and response models
* Execute API requests directly from the browser
* Authenticate using a JWT access token via the **Authorize** button

To access protected endpoints:

1. Authenticate using `POST /api/v1/auth/login`.
2. Copy the returned JWT access token.
3. Click **Authorize** in Swagger UI.
4. Enter the token using the following format:

```text id="kl5x20"
Bearer <your-jwt-token>
```

### Spring Boot Actuator

The application exposes the following public operational endpoints:

| Endpoint           | Description                            |
| ------------------ | -------------------------------------- |
| `/actuator/health` | Returns the application health status  |
| `/actuator/info`   | Returns public application information |

---
## Authentication Flow

The application uses stateless authentication based on JSON Web Tokens (JWT).

### 1. Register a New User

Create a new account using the registration endpoint.

```http
POST /api/v1/auth/register
```

### 2. Authenticate

Authenticate with the registered credentials.

```http
POST /api/v1/auth/login
```

If the credentials are valid, the API returns:

* JWT access token
* Token type (`Bearer`)
* Token expiration time

### 3. Access Protected Resources

Include the JWT in the `Authorization` header for every protected request.

```http
Authorization: Bearer <your-jwt-token>
```

Example:

```http
GET /api/v1/users/me
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### 4. Role-Based Authorization

Some endpoints require additional permissions.

For example:

* `/api/v1/users/me` → any authenticated user
* `/api/v1/users/admin` → users with the `ROLE_ADMIN` authority only

Requests made without a valid token receive **401 Unauthorized** responses, while authenticated users without sufficient privileges receive **403 Forbidden**.

---

## Testing

The project includes both unit and integration tests to ensure application reliability and maintainability.

### Run All Tests

```bash
./mvnw test
```

### Run the Full Verification

```bash
./mvnw clean verify
```

### Test Coverage

The current test suite covers:

* Authentication service
* JWT generation and validation
* Security configuration
* Authentication controller
* Application context loading

All tests are executed automatically by the GitHub Actions CI pipeline on every push and pull request.

---
## Continuous Integration

A GitHub Actions workflow is configured to automatically build and validate the project on every push and pull request.

The pipeline performs the following tasks:

* Checks out the repository
* Sets up Java 21
* Starts a PostgreSQL 17 service
* Configures the required environment variables
* Builds the project with Maven
* Executes the complete test suite

This ensures that every change is validated before being merged and helps prevent regressions.

---
## Roadmap

### Completed

* JWT authentication
* User registration
* User login
* Role-based authorization
* Global exception handling
* Request validation
* OpenAPI documentation
* Spring Boot Actuator integration
* Docker support
* GitHub Actions CI pipeline

### Planned

* Refresh token support
* Email verification
* Forgot password workflow
* Password reset
* Account activation
* User profile update
* Token revocation / blacklist
* Rate limiting
* Audit logging
* Authentication events
* Improved monitoring and observability

---
## Contributing

Contributions, issues and feature requests are welcome.

If you would like to contribute:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes following the Conventional Commits specification.
4. Open a Pull Request describing your changes.

Please ensure that all tests pass before submitting a contribution.

---
## License

This project is licensed under the MIT License.

See the [LICENSE](LICENSE) file for more information.

---

## Author

**Mehdi Zayani**

* GitHub: https://github.com/mehdi-zayani
* Email: [mehdi.zayani.dev@gmail.com](mailto:mehdi.zayani.dev@gmail.com)

If you find this project useful, consider giving it a star on GitHub.

---
