package com.example.bankrate.rate.repository;

import com.example.bankrate.bank.entity.Bank;
import com.example.bankrate.rate.entity.Channel;
import com.example.bankrate.rate.entity.Currency;
import com.example.bankrate.rate.entity.DepositType;
import com.example.bankrate.rate.entity.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRate, Long> {

    @Query("SELECT ir FROM InterestRate ir WHERE ir.bank = :bank " +
           "AND ir.depositType = :depositType " +
           "AND (ir.termMonths = :termMonths OR (ir.termMonths IS NULL AND :termMonths IS NULL)) " +
           "AND ir.currency = :currency " +
           "AND ir.channel = :channel " +
           "ORDER BY ir.collectedAt DESC LIMIT 1")
    Optional<InterestRate> findLatestRate(
            @Param("bank") Bank bank,
            @Param("depositType") DepositType depositType,
            @Param("termMonths") Integer termMonths,
            @Param("currency") Currency currency,
            @Param("channel") Channel channel
    );

    @Query("SELECT ir FROM InterestRate ir WHERE " +
           "(:bankCode IS NULL OR ir.bank.code = :bankCode) AND " +
           "(:termMonths IS NULL OR ir.termMonths = :termMonths) AND " +
           "(:channel IS NULL OR ir.channel = :channel) AND " +
           "(:currency IS NULL OR ir.currency = :currency) " +
           "ORDER BY ir.collectedAt DESC")
    List<InterestRate> filterRates(
            @Param("bankCode") String bankCode,
            @Param("termMonths") Integer termMonths,
            @Param("channel") Channel channel,
            @Param("currency") Currency currency
    );

    @Query("SELECT ir FROM InterestRate ir WHERE ir.termMonths = :termMonths " +
           "AND ir.id IN (SELECT MAX(ir2.id) FROM InterestRate ir2 GROUP BY ir2.bank) " +
           "ORDER BY ir.interestRate DESC")
    List<InterestRate> findBestRatesByTerm(@Param("termMonths") Integer termMonths);

    @Query("SELECT ir FROM InterestRate ir WHERE ir.bank.code IN :bankCodes " +
           "AND ir.termMonths = :termMonths " +
           "AND ir.id IN (SELECT MAX(ir2.id) FROM InterestRate ir2 GROUP BY ir2.bank) " +
           "ORDER BY ir.interestRate DESC")
    List<InterestRate> compareBanks(
            @Param("bankCodes") List<String> bankCodes,
            @Param("termMonths") Integer termMonths
    );
}
