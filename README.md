# bankrate

Bank Interest Rate Intelligence Platform

## Requirements
- Java 21
- Maven 3.8+
- Docker & Docker Compose (optional)

## Build & Test

Build the project and run tests:

```bash
mvn clean package
mvn test
```

## Run locally with Docker Compose

Start Postgres and the app (build image first):

```bash
docker-compose up --build
```

App will be available at http://localhost:8080

## Environment variables
Create a local `.env` file from the provided `.env.example` before running docker-compose:

```bash
cp .env.example .env
# then edit .env to set any values (DB_PASSWORD, etc.)
```

## API
- GET /api/v1/banks - list banks
- GET /api/v1/banks/{code} - get bank by code
- Health endpoints (Actuator): /actuator/health

## CI
A GitHub Actions workflow runs `mvn test` on push and pull requests.

## Notes
- Playwright tests exist under `test/` and require Playwright browsers installed.
- Consider enabling Prometheus metrics via Micrometer for production monitoring.

## Dependency scan

To run OWASP Dependency-Check locally and generate an HTML report, run in
offline/local-data mode or on a machine with internet access. Example:

```bash
mvn org.owasp:dependency-check-maven:check -Dformat=HTML -DoutputDirectory=target/dependency-check-report
```

Note: the scanner may fail to download external vulnerability feeds (NVD/CISA)
if your network blocks those endpoints. The CI pipeline runs the scan in a
non-blocking way and uploads the report artifact so you can review results.
