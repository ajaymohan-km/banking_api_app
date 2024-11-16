package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountSummary {
    private String accountId;
    private String accountNumber;
    private String accountType;
    private BigDecimal currentBalance;
    private BigDecimal availableBalance;
    private String currency;
    private boolean isActive;
    private LocalDateTime lastTransactionDate;
    private BigDecimal monthlyAverageBalance;
    private int pendingTransactions;
}