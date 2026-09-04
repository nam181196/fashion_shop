package com.example.bankrate.rate.controller;

import com.example.bankrate.common.response.ApiResponse;
import com.example.bankrate.rate.dto.InterestRateDto;
import com.example.bankrate.rate.entity.Channel;
import com.example.bankrate.rate.entity.Currency;
import com.example.bankrate.rate.service.InterestRateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rates")
public class InterestRateController {

    private final InterestRateService service;

    public InterestRateController(InterestRateService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<InterestRateDto>> getRates(
            @RequestParam(required = false) String bankCode,
            @RequestParam(required = false) Integer termMonths,
            @RequestParam(required = false) Channel channel,
            @RequestParam(required = false) Currency currency
    ) {
        return ApiResponse.success(service.filterRates(bankCode, termMonths, channel, currency));
    }

    @GetMapping("/best")
    public ApiResponse<List<InterestRateDto>> getBestRates(
            @RequestParam(required = true) Integer termMonths
    ) {
        return ApiResponse.success(service.getBestRates(termMonths));
    }

    @GetMapping("/compare")
    public ApiResponse<List<InterestRateDto>> compareBanks(
            @RequestParam List<String> banks,
            @RequestParam Integer termMonths
    ) {
        return ApiResponse.success(service.compareBanks(banks, termMonths));
    }
}
