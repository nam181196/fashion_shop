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

To run OWASP Dependency-Check locally and generate an HTML report, set an NVD
API key in `NVD_API_KEY` (recommended) or run in offline/local-data mode. Example:

```bash
export NVD_API_KEY=your_nvd_api_key_here
mvn org.owasp:dependency-check-maven:check -Dformat=HTML -DoutputDirectory=target/dependency-check-report
```

If you cannot access NVD/CISA feeds from your network the scanner may fail with
403 errors; in CI use a runner with internet access or an internal mirror of
vulnerability feeds.
