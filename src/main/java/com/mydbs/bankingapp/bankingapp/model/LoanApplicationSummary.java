package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanApplicationSummary {
    private String applicationId;
    private String customerName;
    private String customerId;
    private BigDecimal requestedAmount;
    private String loanType;
    private String status;
    private LocalDateTime applicationDate;
    private Integer creditScore;
    private String riskLevel;
    private String assignedOfficer;
    private Integer processingTimeInDays;
    private String priority;
}