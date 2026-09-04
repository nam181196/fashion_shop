package com.example.bankrate.bank.controller;

import com.example.bankrate.bank.dto.BankDto;
import com.example.bankrate.bank.service.BankService;
import com.example.bankrate.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ApiResponse<List<BankDto>> getAllBanks() {
        return ApiResponse.success(bankService.getAllBanks());
    }

    @GetMapping("/{code}")
    public ApiResponse<BankDto> getBankByCode(@PathVariable String code) {
        return ApiResponse.success(bankService.getBankByCode(code));
    }
}
