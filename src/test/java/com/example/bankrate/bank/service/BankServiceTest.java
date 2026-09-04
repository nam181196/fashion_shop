package com.example.bankrate.bank.service;

import com.example.bankrate.bank.dto.BankDto;
import com.example.bankrate.bank.entity.Bank;
import com.example.bankrate.bank.repository.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BankServiceTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankService bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBanks_returnsDtos() {
        Bank b1 = new Bank();
        b1.setCode("ABC");
        b1.setName("ABC Bank");

        when(bankRepository.findAll()).thenReturn(List.of(b1));

        List<BankDto> dtos = bankService.getAllBanks();

        assertThat(dtos).hasSize(1);
        assertThat(dtos.get(0).getCode()).isEqualTo("ABC");
    }

    @Test
    void getBankByCode_returnsDto() {
        Bank b = new Bank();
        b.setCode("XYZ");
        b.setName("XYZ Bank");

        when(bankRepository.findByCode("XYZ")).thenReturn(Optional.of(b));

        BankDto dto = bankService.getBankByCode("XYZ");

        assertThat(dto.getCode()).isEqualTo("XYZ");
    }
}
