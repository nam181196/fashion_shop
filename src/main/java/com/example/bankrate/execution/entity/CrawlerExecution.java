package com.example.bankrate.execution.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "crawler_execution")
public class CrawlerExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_code", nullable = false, length = 50)
    private String bankCode;

    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;

    @Column(name = "finished_at")
    private OffsetDateTime finishedAt;

    @Column(nullable = false, length = 20)
    private String status; // RUNNING, SUCCESS, FAILED

    @Column(name = "records_fetched")
    private Integer recordsFetched = 0;

    @Column(name = "records_saved")
    private Integer recordsSaved = 0;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    public CrawlerExecution() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }
    public OffsetDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(OffsetDateTime startedAt) { this.startedAt = startedAt; }
    public OffsetDateTime getFinishedAt() { return finishedAt; }
    public void setFinishedAt(OffsetDateTime finishedAt) { this.finishedAt = finishedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getRecordsFetched() { return recordsFetched; }
    public void setRecordsFetched(Integer recordsFetched) { this.recordsFetched = recordsFetched; }
    public Integer getRecordsSaved() { return recordsSaved; }
    public void setRecordsSaved(Integer recordsSaved) { this.recordsSaved = recordsSaved; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
