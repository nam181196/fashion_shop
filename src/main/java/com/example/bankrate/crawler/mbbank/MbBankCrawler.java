package com.example.bankrate.crawler.mbbank;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MbBankCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public MbBankCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "MB";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("MB", "https://webgia.com/lai-suat/mbbank/");
    }
}
