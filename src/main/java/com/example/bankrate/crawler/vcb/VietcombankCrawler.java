package com.example.bankrate.crawler.vcb;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VietcombankCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public VietcombankCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "VCB";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("VCB", "https://webgia.com/lai-suat/vietcombank/");
    }
}
