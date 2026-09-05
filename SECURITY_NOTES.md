Dependency scan and NVD API key
================================

CI currently runs OWASP dependency-check in non-blocking mode when the `NVD_API_KEY` secret is not present. To enable deterministic, full scans against the NVD API you should:

1. Create a repository secret named `NVD_API_KEY` containing your NVD API key.
2. In GitHub Actions settings ensure the secret is available to the CI workflow branch that runs the check.
3. Optionally, rotate the key periodically and restrict access.

If you cannot obtain an NVD key, consider configuring dependency-check to use local mirrors or to accept non-fatal failures in CI (current state).
