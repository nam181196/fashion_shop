package com.example.bankrate.crawler.core;

import com.example.bankrate.rate.entity.Channel;
import com.example.bankrate.rate.entity.Currency;
import com.example.bankrate.rate.entity.DepositType;
import org.springframework.stereotype.Component;

@Component
public class InterestRateNormalizer {

    public Integer normalizeTermMonths(String rawTerm) {
        if (rawTerm == null || rawTerm.trim().isEmpty()) return null;
        String lower = rawTerm.toLowerCase().trim();
        
        // Match things like "1 tháng", "01 tháng", "1M"
        if (lower.contains("tháng") || lower.endsWith("m")) {
            String numberOnly = lower.replaceAll("[^0-9]", "");
            if (!numberOnly.isEmpty()) {
                return Integer.parseInt(numberOnly);
            }
        }
        
        // Match things like "30 ngày" (approximate to 1 month or leave as custom logic)
        if (lower.contains("ngày") || lower.endsWith("d")) {
            String numberOnly = lower.replaceAll("[^0-9]", "");
            if (!numberOnly.isEmpty()) {
                int days = Integer.parseInt(numberOnly);
                return Math.max(1, days / 30); // Simple approximation
            }
        }
        
        return null;
    }

    public DepositType normalizeDepositType(String rawType) {
        if (rawType == null) return DepositType.SAVING; // default
        String upper = rawType.toUpperCase();
        if (upper.contains("CHECKING") || upper.contains("THANH TOÁN")) {
            return DepositType.CHECKING;
        }
        return DepositType.SAVING;
    }

    public Currency normalizeCurrency(String rawCurrency) {
        if (rawCurrency == null) return Currency.VND;
        String upper = rawCurrency.toUpperCase();
        if (upper.contains("USD")) return Currency.USD;
        if (upper.contains("EUR")) return Currency.EUR;
        return Currency.VND;
    }

    public Channel normalizeChannel(String rawChannel) {
        if (rawChannel == null) return Channel.COUNTER;
        String upper = rawChannel.toUpperCase();
        if (upper.contains("ONLINE") || upper.contains("APP") || upper.contains("WEB")) {
            return Channel.ONLINE;
        }
        return Channel.COUNTER;
    }
}
