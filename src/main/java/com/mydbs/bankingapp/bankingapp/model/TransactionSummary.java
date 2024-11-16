package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionSummary {
    private String transactionId;
    private String transactionType;
    private BigDecimal amount;
    private String description;
    private LocalDateTime timestamp;
    private String status;
    private String recipientName;
    private String recipientAccount;
    private String category;
}