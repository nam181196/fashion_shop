package com.example.bankrate.crawler.core;

public class RawInterestRate {
    private String bankCode;
    private String depositType;
    private String term;
    private String interestRate;
    private String currency;
    private String channel;

    public RawInterestRate() {}

    public RawInterestRate(String bankCode, String depositType, String term, String interestRate, String currency, String channel) {
        this.bankCode = bankCode;
        this.depositType = depositType;
        this.term = term;
        this.interestRate = interestRate;
        this.currency = currency;
        this.channel = channel;
    }

    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }
    public String getDepositType() { return depositType; }
    public void setDepositType(String depositType) { this.depositType = depositType; }
    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
    public String getInterestRate() { return interestRate; }
    public void setInterestRate(String interestRate) { this.interestRate = interestRate; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
}
