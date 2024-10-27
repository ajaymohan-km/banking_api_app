package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "loan_applications")
public class LoanApplication {
    @Id
    private String id;
    private String userId;
    private BigDecimal amount;
    private String status;  
    private String loanType;
    private LocalDateTime applicationDate;
    private String description;
    private String bankerId; 
    private LocalDateTime processedDate;
}