package com.example.bankrate.crawler.core;

import java.util.List;

public interface InterestRateCrawler {
    
    /**
     * Get the bank code that this crawler supports.
     */
    String getBankCode();

    /**
     * Crawl the raw interest rate data from the bank's source.
     */
    List<RawInterestRate> crawl();
}
