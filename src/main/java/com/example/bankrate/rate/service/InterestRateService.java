package com.example.bankrate.rate.service;

import com.example.bankrate.rate.dto.InterestRateDto;
import com.example.bankrate.rate.entity.Channel;
import com.example.bankrate.rate.entity.Currency;
import com.example.bankrate.rate.repository.InterestRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class InterestRateService {

    private final InterestRateRepository repository;

    public InterestRateService(InterestRateRepository repository) {
        this.repository = repository;
    }

    public List<InterestRateDto> filterRates(String bankCode, Integer termMonths, Channel channel, Currency currency) {
        return repository.filterRates(bankCode, termMonths, channel, currency).stream()
                .map(InterestRateDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<InterestRateDto> getBestRates(Integer termMonths) {
        return repository.findBestRatesByTerm(termMonths).stream()
                .map(InterestRateDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<InterestRateDto> compareBanks(List<String> bankCodes, Integer termMonths) {
        return repository.compareBanks(bankCodes, termMonths).stream()
                .map(InterestRateDto::fromEntity)
                .collect(Collectors.toList());
    }
}
