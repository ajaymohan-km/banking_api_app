package com.mydbs.bankingapp.bankingapp.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.AdminDashboard;
import com.mydbs.bankingapp.bankingapp.model.BankerDashboard;
import com.mydbs.bankingapp.bankingapp.model.CustomerDashboard;
import com.mydbs.bankingapp.bankingapp.model.TransactionStats;
import com.mydbs.bankingapp.bankingapp.model.UserStats;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final LoanService loanService;
    private final UserService userService;

    public CustomerDashboard getCustomerDashboard() {
        CustomerDashboard dashboard = new CustomerDashboard();
        String userId = userService.getCurrentUserId();
        
        dashboard.setAccounts(accountService.getAccountSummaries(userId));
        dashboard.setTotalBalance(accountService.calculateTotalBalance(userId));
        dashboard.setRecentTransactions(transactionService.getRecentTransactions(userId, 5));
        dashboard.setActiveLoans(loanService.getActiveLoanSummaries(userId));
   
        
        return dashboard;
    }

    public BankerDashboard getBankerDashboard() {
        BankerDashboard dashboard = new BankerDashboard();
        
        dashboard.setPendingLoanApplications(loanService.countPendingApplications());
        dashboard.setTotalCustomers(userService.countCustomers());
        dashboard.setRecentLoanApplications(loanService.getRecentApplications(10));
        
        return dashboard;
    }

    public AdminDashboard getAdminDashboard() {
        AdminDashboard dashboard = new AdminDashboard();
        

        dashboard.setUserStats(userService.collectUserStats());
        dashboard.setTransactionStats(transactionService.collectTransactionStats());
        
        return dashboard;
    }
   
}