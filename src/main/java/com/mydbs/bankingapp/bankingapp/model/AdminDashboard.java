package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;

@Data
public class AdminDashboard {

    private UserStats userStats;
    private TransactionStats transactionStats;

}