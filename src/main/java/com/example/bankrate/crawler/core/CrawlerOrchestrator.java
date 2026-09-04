package com.example.bankrate.crawler.core;

import com.example.bankrate.bank.entity.Bank;
import com.example.bankrate.bank.repository.BankRepository;
import com.example.bankrate.common.exception.BankNotFoundException;
import com.example.bankrate.execution.entity.CrawlerExecution;
import com.example.bankrate.execution.repository.CrawlerExecutionRepository;
import com.example.bankrate.rate.entity.Channel;
import com.example.bankrate.rate.entity.Currency;
import com.example.bankrate.rate.entity.DepositType;
import com.example.bankrate.rate.entity.InterestRate;
import com.example.bankrate.rate.repository.InterestRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CrawlerOrchestrator {

    private static final Logger log = LoggerFactory.getLogger(CrawlerOrchestrator.class);

    private final Map<String, InterestRateCrawler> crawlerMap;
    private final BankRepository bankRepository;
    private final InterestRateRepository rateRepository;
    private final CrawlerExecutionRepository executionRepository;
    private final InterestRateNormalizer normalizer;

    public CrawlerOrchestrator(
            List<InterestRateCrawler> crawlers,
            BankRepository bankRepository,
            InterestRateRepository rateRepository,
            CrawlerExecutionRepository executionRepository,
            InterestRateNormalizer normalizer) {
        this.crawlerMap = crawlers.stream()
                .collect(Collectors.toMap(InterestRateCrawler::getBankCode, Function.identity()));
        this.bankRepository = bankRepository;
        this.rateRepository = rateRepository;
        this.executionRepository = executionRepository;
        this.normalizer = normalizer;
    }

    @Transactional
    public void runCrawler(String bankCode) {
        log.info("Starting crawler for bank: {}", bankCode);
        
        InterestRateCrawler crawler = crawlerMap.get(bankCode);
        if (crawler == null) {
            log.error("No crawler found for bank code: {}", bankCode);
            throw new IllegalArgumentException("Crawler not found for bank: " + bankCode);
        }

        Bank bank = bankRepository.findByCode(bankCode)
                .orElseThrow(() -> new BankNotFoundException("Bank not found: " + bankCode));

        CrawlerExecution execution = new CrawlerExecution();
        execution.setBankCode(bankCode);
        execution.setStartedAt(OffsetDateTime.now());
        execution.setStatus("RUNNING");
        execution = executionRepository.save(execution);

        try {
            List<RawInterestRate> rawRates = crawler.crawl();
            execution.setRecordsFetched(rawRates.size());
            log.info("Crawler fetched {} records for {}", rawRates.size(), bankCode);

            int savedCount = 0;
            OffsetDateTime now = OffsetDateTime.now();

            for (RawInterestRate raw : rawRates) {
                try {
                    boolean saved = processAndSaveRate(bank, raw, now);
                    if (saved) savedCount++;
                } catch (Exception e) {
                    log.error("Error processing rate for {}: {}", bankCode, raw, e);
                }
            }

            execution.setRecordsSaved(savedCount);
            execution.setStatus("SUCCESS");
            execution.setFinishedAt(OffsetDateTime.now());
            executionRepository.save(execution);
            log.info("Crawler completed for {}. Saved: {}", bankCode, savedCount);

        } catch (Exception e) {
            log.error("Crawler failed for {}", bankCode, e);
            execution.setStatus("FAILED");
            execution.setErrorMessage(e.getMessage());
            execution.setFinishedAt(OffsetDateTime.now());
            executionRepository.save(execution);
            throw e;
        }
    }

    private boolean processAndSaveRate(Bank bank, RawInterestRate raw, OffsetDateTime collectedAt) {
        Integer termMonths = normalizer.normalizeTermMonths(raw.getTerm());
        DepositType depositType = normalizer.normalizeDepositType(raw.getDepositType());
        Currency currency = normalizer.normalizeCurrency(raw.getCurrency());
        Channel channel = normalizer.normalizeChannel(raw.getChannel());
        BigDecimal rateValue = new BigDecimal(raw.getInterestRate());

        Optional<InterestRate> latestOpt = rateRepository.findLatestRate(
                bank, depositType, termMonths, currency, channel
        );

        if (latestOpt.isPresent()) {
            InterestRate latest = latestOpt.get();
            // If rate is identical, just update collectedAt
            if (latest.getInterestRate().compareTo(rateValue) == 0) {
                latest.setCollectedAt(collectedAt);
                rateRepository.save(latest);
                return true;
            }
        }

        // Rate changed or new rate, insert new row
        InterestRate newRate = new InterestRate();
        newRate.setBank(bank);
        newRate.setDepositType(depositType);
        newRate.setTermMonths(termMonths);
        newRate.setTermLabel(raw.getTerm());
        newRate.setInterestRate(rateValue);
        newRate.setCurrency(currency);
        newRate.setChannel(channel);
        newRate.setCollectedAt(collectedAt);
        
        rateRepository.save(newRate);
        return true;
    }
}
