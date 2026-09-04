package com.example.bankrate.bank.dto;

import com.example.bankrate.bank.entity.Bank;
import java.time.OffsetDateTime;

public class BankDto {
    private Long id;
    private String code;
    private String name;
    private String website;
    private Boolean active;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public BankDto() {}

    public static BankDto fromEntity(Bank bank) {
        if (bank == null) return null;
        BankDto dto = new BankDto();
        dto.setId(bank.getId());
        dto.setCode(bank.getCode());
        dto.setName(bank.getName());
        dto.setWebsite(bank.getWebsite());
        dto.setActive(bank.getActive());
        dto.setCreatedAt(bank.getCreatedAt());
        dto.setUpdatedAt(bank.getUpdatedAt());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
