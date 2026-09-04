package com.example.bankrate.crawler.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebgiaPlaywrightScraper {
    private static final Logger log = LoggerFactory.getLogger(WebgiaPlaywrightScraper.class);

    public List<RawInterestRate> scrape(String bankCode, String url) {
        List<RawInterestRate> rates = new ArrayList<>();
        log.info("Starting Playwright to scrape {}", url);

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate(url);
            
            // Wait for the first table to load
            page.waitForSelector("table.table-radius");
            
            // We only want the VND Saving rates, which is usually the first table
            Locator rows = page.locator("table.table-radius").first().locator("tr");
            
            for (int i = 0; i < rows.count(); i++) {
                String rowText = rows.nth(i).innerText();
                String[] columns = rowText.split("\t");
                if (columns.length >= 2) {
                    String term = columns[0].trim();
                    String rateStr = columns[1].trim();
                    
                    // Skip header rows
                    if (term.equalsIgnoreCase("Kỳ hạn") || rateStr.equalsIgnoreCase("Lãi suất")) {
                        continue;
                    }
                    
                    // Clean up rate string (e.g. "2,10%" -> "2.10")
                    rateStr = rateStr.replace("%", "").replace(",", ".").trim();
                    
                    // Only process valid numbers
                    if (rateStr.matches("\\d+(\\.\\d+)?")) {
                        rates.add(new RawInterestRate(bankCode, "SAVING", term, rateStr, "VND", "COUNTER"));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to scrape with Playwright", e);
            throw new RuntimeException("Playwright scraping failed", e);
        }
        return rates;
    }
}
