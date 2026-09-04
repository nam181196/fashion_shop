package com.example.bankrate.bank.service;

import com.example.bankrate.bank.dto.BankDto;
import com.example.bankrate.bank.entity.Bank;
import com.example.bankrate.bank.repository.BankRepository;
import com.example.bankrate.common.exception.BankNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankDto> getAllBanks() {
        return bankRepository.findAll().stream()
                .map(BankDto::fromEntity)
                .collect(Collectors.toList());
    }

    public BankDto getBankByCode(String code) {
        Bank bank = bankRepository.findByCode(code)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with code: " + code));
        return BankDto.fromEntity(bank);
    }
}
