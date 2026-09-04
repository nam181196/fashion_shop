package com.example.bankrate;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;

public class PlaywrightE2ETest {

    @Test
    void basicHomePageLoad() {
        Assumptions.assumeTrue("1".equals(System.getenv("RUN_PLAYWRIGHT")), "Skipping Playwright tests");

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();
            String base = System.getenv("PLAYWRIGHT_BASE_URL");
            if (base == null || base.isBlank()) base = "http://localhost:8081";
            page.navigate(base);
            page.waitForLoadState();
        }
    }
}
