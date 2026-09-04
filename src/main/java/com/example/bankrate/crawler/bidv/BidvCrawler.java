package com.example.bankrate.crawler.bidv;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BidvCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public BidvCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "BIDV";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("BIDV", "https://webgia.com/lai-suat/bidv/");
    }
}
