CV / Resume bullets (Vietnamese)

- Phát triển nền tảng thu thập và hiển thị lãi suất ngân hàng bằng Spring Boot (Java 21), Spring Data JPA và Flyway để quản lý migrations.
- Xây dựng frontend SPA bằng React + Vite; cấu hình Docker multi-stage build và Nginx để phục vụ production.
- Thiết lập CI/CD cơ bản với GitHub Actions: build, tests, dependency scan (OWASP Dependency-Check non-blocking) và Dependabot cho cập nhật dependency.
- Thêm quan sát và telemetry: Spring Boot Actuator + Micrometer Prometheus registry.
- Viết unit tests (JUnit + Mockito) và chuẩn bị Playwright E2E skeleton (được bật bằng biến môi trường).
- Tích hợp SPA production build trực tiếp vào `src/main/resources/static` để đơn giản hóa deployment container.

CV / Resume bullets (English)

- Implemented a bank interest rate intelligence platform using Spring Boot (Java 21), Spring Data JPA and Flyway for schema migrations.
- Built a React + Vite SPA and Dockerized the app with multi-stage builds and Nginx for static serving.
- Added CI with GitHub Actions (build & tests), OWASP dependency scan (non-blocking when NVD key missing) and Dependabot for dependency updates.
- Implemented observability via Spring Boot Actuator and Micrometer Prometheus registry.
- Wrote unit tests (JUnit + Mockito) and scaffolded Playwright E2E tests behind an environment flag for safe CI runs.
- Embedded frontend production build into the Spring Boot jar for streamlined deployments.
