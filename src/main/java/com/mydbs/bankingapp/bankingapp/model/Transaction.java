package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String userId; 
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String status;
    private String description;
}
