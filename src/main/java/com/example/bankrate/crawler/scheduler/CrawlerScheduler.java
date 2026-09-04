package com.example.bankrate.crawler.scheduler;

import com.example.bankrate.bank.entity.Bank;
import com.example.bankrate.bank.repository.BankRepository;
import com.example.bankrate.crawler.core.CrawlerOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlerScheduler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerScheduler.class);
    private final CrawlerOrchestrator orchestrator;
    private final BankRepository bankRepository;

    public CrawlerScheduler(CrawlerOrchestrator orchestrator, BankRepository bankRepository) {
        this.orchestrator = orchestrator;
        this.bankRepository = bankRepository;
    }

    @Scheduled(cron = "${crawler.schedule.cron}")
    public void runAllCrawlers() {
        log.info("Starting scheduled crawler execution");
        List<Bank> activeBanks = bankRepository.findAll().stream()
                .filter(Bank::getActive)
                .toList();

        for (Bank bank : activeBanks) {
            try {
                orchestrator.runCrawler(bank.getCode());
            } catch (Exception e) {
                log.error("Scheduled crawler failed for bank: {}", bank.getCode(), e);
            }
        }
        log.info("Completed scheduled crawler execution");
    }
}
