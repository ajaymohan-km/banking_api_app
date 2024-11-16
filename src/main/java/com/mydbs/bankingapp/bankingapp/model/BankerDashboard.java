package com.mydbs.bankingapp.bankingapp.model;

import java.util.List;

import lombok.Data;

@Data
public class BankerDashboard {
    private int pendingLoanApplications;
    private int totalCustomers;
    private List<LoanApplicationSummary> recentLoanApplications;
    private List<CustomerActivity> recentCustomerActivities;
    private DailyTransactionStats transactionStats;
}