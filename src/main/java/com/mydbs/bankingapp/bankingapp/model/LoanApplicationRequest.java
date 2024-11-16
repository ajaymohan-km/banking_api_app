package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class LoanApplicationRequest {
    private BigDecimal amount;
    private String purpose;
    private Integer termInMonths;
    private String employmentStatus;
    private BigDecimal monthlyIncome;
}