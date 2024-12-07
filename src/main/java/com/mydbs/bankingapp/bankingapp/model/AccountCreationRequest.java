package com.mydbs.bankingapp.bankingapp.model;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountCreationRequest {
    private String accountType;
    private BigDecimal initialDeposit;
}
