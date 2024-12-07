package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;

@Data
public class AccountLinkRequest {
    private String accountNumber;
    private String accountType;
}
