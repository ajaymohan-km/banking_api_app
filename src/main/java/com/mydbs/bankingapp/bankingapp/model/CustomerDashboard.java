package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerDashboard {
    private BigDecimal totalBalance;
    private List<AccountSummary> accounts;
    private List<TransactionSummary> recentTransactions;
    private List<LoanSummary> activeLoans;
    private List<NotificationDto> notifications;
}