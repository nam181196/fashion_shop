package com.example.bankrate.crawler.vietinbank;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VietinBankCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public VietinBankCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "CTG";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("CTG", "https://webgia.com/lai-suat/vietinbank/");
    }
}
