package com.example.bankrate.crawler.techcombank;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TechcombankCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public TechcombankCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "TCB";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("TCB", "https://webgia.com/lai-suat/techcombank/");
    }
}
