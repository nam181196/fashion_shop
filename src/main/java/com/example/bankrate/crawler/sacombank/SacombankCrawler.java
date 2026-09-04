package com.example.bankrate.crawler.sacombank;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SacombankCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public SacombankCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "STB";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("STB", "https://webgia.com/lai-suat/sacombank/");
    }
}
