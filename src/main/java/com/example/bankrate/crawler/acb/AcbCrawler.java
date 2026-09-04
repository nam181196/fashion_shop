package com.example.bankrate.crawler.acb;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AcbCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public AcbCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "ACB";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("ACB", "https://webgia.com/lai-suat/acb/");
    }
}
