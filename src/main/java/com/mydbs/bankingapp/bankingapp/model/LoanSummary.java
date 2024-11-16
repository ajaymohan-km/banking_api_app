package com.mydbs.bankingapp.bankingapp.model;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanSummary {
    private String loanId;
    private String loanType;
    private BigDecimal principalAmount;
    private BigDecimal remainingAmount;
    private double interestRate;
    private String status;
    private LocalDate startDate;
    private LocalDate dueDate;
    
    private int remainingInstallments;
    private BigDecimal monthlyPayment;
}