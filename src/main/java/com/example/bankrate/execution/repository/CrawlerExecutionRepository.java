package com.example.bankrate.execution.repository;

import com.example.bankrate.execution.entity.CrawlerExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlerExecutionRepository extends JpaRepository<CrawlerExecution, Long> {
}
