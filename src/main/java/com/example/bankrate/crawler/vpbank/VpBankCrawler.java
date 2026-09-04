package com.example.bankrate.crawler.vpbank;

import com.example.bankrate.crawler.core.InterestRateCrawler;
import com.example.bankrate.crawler.core.RawInterestRate;
import com.example.bankrate.crawler.core.WebgiaPlaywrightScraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VpBankCrawler implements InterestRateCrawler {

    private final WebgiaPlaywrightScraper scraper;

    public VpBankCrawler(WebgiaPlaywrightScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public String getBankCode() {
        return "VPB";
    }

    @Override
    public List<RawInterestRate> crawl() {
        return scraper.scrape("VPB", "https://webgia.com/lai-suat/vpbank/");
    }
}
