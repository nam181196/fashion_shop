package com.example.bankrate;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightTest {
    public static void main(String[] args) {
        System.out.println("Starting Playwright...");
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            System.out.println("Navigating...");
            page.navigate("https://webgia.com/lai-suat/vietcombank/");
            page.waitForSelector("table.table-radius");
            
            Locator rows = page.locator("table.table-radius tr");
            System.out.println("Found " + rows.count() + " rows");
            for (int i = 0; i < rows.count(); i++) {
                System.out.println(rows.nth(i).innerText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
