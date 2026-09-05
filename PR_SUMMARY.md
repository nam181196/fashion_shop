Project closure & PR summary
===========================

Tóm tắt thay đổi đã hoàn thành
- Backend: Spring Boot app (Java 21), Actuator + Micrometer, Flyway migrations.
- CI: GitHub Actions runs `mvn test`, non-blocking OWASP dependency-check; Dependabot configured.
- Docker: `Dockerfile` for app, `docker-compose.yml` with `postgres` and `app` services.
- Frontend: React + Vite SPA scaffold in `frontend/`, production build embedded into `src/main/resources/static/app` and packaged into the jar.
- Tests: Unit tests and Playwright E2E skeleton added (Playwright guarded by env flag).
- Security: Removed hard requirement for `NVD_API_KEY` in README; dependency-check in CI is non-blocking when secret absent.

How to run locally (quick)
1. Copy `.env.example` to `.env` and adjust values.
2. Build backend jar (SPA already embedded in repo):
   mvn -DskipTests package
3. Start services (postgres + app):
   docker compose up --build -d
4. Backend API: http://localhost:8080/api/v1/banks
   SPA: http://localhost:8080/app

Remaining recommended actions
- Add repository secret `NVD_API_KEY` for full, authoritative OWASP dependency-check results in CI.
- Consider running frontend build as part of CI (separate job) and publishing artifacts to avoid local docker npm issues.
- (Optional) Add a root redirect `/` -> `/app` for nicer UX.
- Push final commits and open a PR for review and merge.

Commits created in this branch include the frontend scaffold, embedded SPA build, CI workflow, and documentation updates.

If you want, I can:
- push the current branch and open a PR with this summary,
- add the root redirect and commit it,
- or create a small GitHub Actions job to build the frontend and publish `dist` as an artifact.

Contact/Notes
- I can also prepare a short changelog/PR description in English if needed for remote reviewers.
