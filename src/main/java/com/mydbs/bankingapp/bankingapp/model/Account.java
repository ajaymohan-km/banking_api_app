package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Data
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String userId;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private boolean active = true;
}