package com.example.bankrate.execution.controller;

import com.example.bankrate.common.response.ApiResponse;
import com.example.bankrate.crawler.core.CrawlerOrchestrator;
import com.example.bankrate.execution.entity.CrawlerExecution;
import com.example.bankrate.execution.repository.CrawlerExecutionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/crawlers")
public class AdminController {

    private final CrawlerOrchestrator orchestrator;
    private final CrawlerExecutionRepository executionRepository;

    public AdminController(CrawlerOrchestrator orchestrator, CrawlerExecutionRepository executionRepository) {
        this.orchestrator = orchestrator;
        this.executionRepository = executionRepository;
    }

    @PostMapping("/{bankCode}/run")
    public ApiResponse<String> runCrawler(@PathVariable String bankCode) {
        try {
            orchestrator.runCrawler(bankCode);
            return ApiResponse.success("Crawler executed successfully for " + bankCode);
        } catch (Exception e) {
            return ApiResponse.error("CRAWLER_ERROR", e.getMessage());
        }
    }

    @GetMapping("/executions")
    public ApiResponse<Page<CrawlerExecution>> getExecutions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("startedAt").descending());
        return ApiResponse.success(executionRepository.findAll(pageRequest));
    }
}
