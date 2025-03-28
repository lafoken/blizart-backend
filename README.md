# blizart-backend

The backend service for the Blizart Ukraine Info Hub project. This service handles data management, business logic, and provides a RESTful API for the frontend application.

## Features

* User Authentication & Authorization (JWT)
* REST API endpoints for managing informational content (adjust as needed)
* Database interaction via Spring Data JPA
* Database migrations managed by Liquibase

## Built With

* **Java 17+**
* **Spring Boot 3+** - Application Framework
* **Spring Data JPA** - Data Access
* **Spring Security** - Authentication & Authorization
* **PostgreSQL** - Relational Database
* **Liquibase** - Database Migrations
* **JWT (Java JWT)** - JSON Web Token implementation
* **Lombok** - Boilerplate Code Reduction
* **Gradle** - Build Tool
* **Docker** - Containerization

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 17 or later
* Gradle 7+
* PostgreSQL Database running
* Docker (optional, for containerized deployment)

### Configuration

1.  Set up your PostgreSQL database.
2.  Configure the database connection, JWT secret, and other settings in `src/main/resources/application.properties` (or `application.yml`). You might want to use environment variables or Spring profiles for sensitive data.
    * `spring.datasource.url`
    * `spring.datasource.username`
    * `spring.datasource.password`
    * `jwt.secret` (Ensure this is a strong, secure secret)
    * `liquibase.change-log` (usually configured automatically)

### Running Locally

1.  Clone the repository:
    ```bash
    git clone git@github.com:lafoken/blizart-backend.git
    cd blizart-backend
    ```
2.  Build the project using Gradle:
    ```bash
    ./gradlew build
    ```
3.  Run the application:
    ```bash
    ./gradlew bootRun
    # Or run the JAR file directly
    # java -jar build/libs/blizart-backend-*.jar
    ```
The application should start on the configured port (default: 8081).

### Running with Docker

1.  Ensure Docker is running.
2.  Build the Docker image:
    ```bash
    docker build -t blizart-backend .
    ```
3.  Run using Docker Compose (adjust `docker-compose.yml` as needed, especially for database connection from within the container network):
    ```bash
    docker-compose up -d
    ```

## API Endpoints

(Describe your main API endpoints here. Consider adding Swagger/OpenAPI documentation for a better developer experience.)

* `POST /api/auth/login` - Authenticate user, returns JWT
* `POST /api/auth/register` - Register new user
* `GET /api/resource` - Example protected resource
