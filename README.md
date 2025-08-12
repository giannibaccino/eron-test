# Eron test

This is a simple Java Spring Boot application.  
It calls an external movie API, processes the results, and exposes an endpoint to return directors who have directed more than a given number of movies (threshold variable).

Because this is a small test, the project was kept intentionally simple:

- No DTOs or domain models were created, JSON responses are parsed directly into JSONObject and JSONArray.
- No service layer abstractions beyond the minimal requirement.
- No unit tests or validation logic beyond the spring boot defaults.

---

## Repository
Source code is available on GitHub:

https://github.com/giannibaccino/eron-test.git

---

## Technologies

- Java 17
- Spring Boot
- Maven
- Docker

---

## Test App Deployment

The application is deployed on Render using Docker.

You can test it with a simple GET request:

https://eron-test.onrender.com/api/directors?threshold=4

## Run locally (without Docker)

```bash
mvn clean package -DskipTests
java -jar target/*.jar
```

The app will run in port 8080

## Run locally (without Docker)

```bash
docker build -t movie-service .
docker run -p 8080:8080 movie-service
```
