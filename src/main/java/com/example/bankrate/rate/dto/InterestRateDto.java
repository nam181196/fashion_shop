package com.example.bankrate.rate.dto;

import com.example.bankrate.rate.entity.Channel;
import com.example.bankrate.rate.entity.Currency;
import com.example.bankrate.rate.entity.DepositType;
import com.example.bankrate.rate.entity.InterestRate;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class InterestRateDto {
    private Long id;
    private String bankCode;
    private DepositType depositType;
    private Integer termMonths;
    private String termLabel;
    private BigDecimal interestRate;
    private Currency currency;
    private Channel channel;
    private OffsetDateTime collectedAt;

    public static InterestRateDto fromEntity(InterestRate entity) {
        if (entity == null) return null;
        InterestRateDto dto = new InterestRateDto();
        dto.setId(entity.getId());
        if (entity.getBank() != null) {
            dto.setBankCode(entity.getBank().getCode());
        }
        dto.setDepositType(entity.getDepositType());
        dto.setTermMonths(entity.getTermMonths());
        dto.setTermLabel(entity.getTermLabel());
        dto.setInterestRate(entity.getInterestRate());
        dto.setCurrency(entity.getCurrency());
        dto.setChannel(entity.getChannel());
        dto.setCollectedAt(entity.getCollectedAt());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }
    public DepositType getDepositType() { return depositType; }
    public void setDepositType(DepositType depositType) { this.depositType = depositType; }
    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }
    public String getTermLabel() { return termLabel; }
    public void setTermLabel(String termLabel) { this.termLabel = termLabel; }
    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }
    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }
    public Channel getChannel() { return channel; }
    public void setChannel(Channel channel) { this.channel = channel; }
    public OffsetDateTime getCollectedAt() { return collectedAt; }
    public void setCollectedAt(OffsetDateTime collectedAt) { this.collectedAt = collectedAt; }
}
