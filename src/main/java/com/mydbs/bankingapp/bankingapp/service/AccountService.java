package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mydbs.bankingapp.bankingapp.model.Account;
import com.mydbs.bankingapp.bankingapp.model.AccountCreationRequest;
import com.mydbs.bankingapp.bankingapp.model.AccountLinkRequest;
import com.mydbs.bankingapp.bankingapp.model.AccountSummary;
import com.mydbs.bankingapp.bankingapp.model.Transaction;
import com.mydbs.bankingapp.bankingapp.model.TransferRequest;
import com.mydbs.bankingapp.bankingapp.model.User;
import com.mydbs.bankingapp.bankingapp.repository.AccountRepository;
import com.mydbs.bankingapp.bankingapp.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public List<Account> getCurrentUserAccounts() {
        String userId = userService.getCurrentUserId();
        return accountRepository.findByUserId(userId);
    }

    @Transactional
    public Transaction transfer(TransferRequest request) {
        Account fromAccount = accountRepository.findById(request.getFromAccountId())
            .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account toAccount = accountRepository.findById(request.getToAccountId())
            .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        String userId = userService.getCurrentUserId();

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromAccount.getId());
        transaction.setUserId(userId);
        transaction.setToAccountId(toAccount.getId());
        transaction.setAmount(request.getAmount());

        return transactionRepository.save(transaction);
    }
  







    
        public List<AccountSummary> getAccountSummaries(String userId) {
        return accountRepository.findByUserId(userId)
            .stream()
            .map(account -> {
                AccountSummary summary = new AccountSummary();
                summary.setAccountId(account.getId());
                summary.setAccountNumber(account.getAccountNumber());
                summary.setCurrentBalance(account.getBalance());
                summary.setAccountType(account.getAccountType());
                return summary;
            })
            .collect(Collectors.toList());
    }

    public BigDecimal calculateTotalBalance(String userId) {
        return accountRepository.findByUserId(userId)
            .stream()
            .map(Account::getBalance)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Account createAccount(AccountCreationRequest request) {
        // Get current authenticated user
        User currentUser = userService.getCurrentUser();
        
        Account account = new Account();
        account.setUserId(currentUser.getId());
        account.setAccountType(request.getAccountType());
        account.setBalance(request.getInitialDeposit());
        account.setAccountNumber(generateAccountNumber());
        account.setActive(true);
        
        return accountRepository.save(account);
    }

    private String generateAccountNumber() {
        // Generate a unique 10-digit account number
        return String.format("%010d", new Random().nextInt(1000000000));
    }
    
public Account linkExistingAccount(AccountLinkRequest request) {
    User currentUser = userService.getCurrentUser();
    
    // Verify account exists and not already linked
    Account existingAccount = accountRepository.findByAccountNumber(request.getAccountNumber())
        .orElseThrow(() -> new RuntimeException("Account not found"));
        
    if (existingAccount.getUserId() != null) {
        throw new IllegalStateException("Account already linked to a user");
    }
    
    existingAccount.setUserId(currentUser.getId());
    existingAccount.setAccountType(request.getAccountType());
    existingAccount.setActive(true);
    
    return accountRepository.save(existingAccount);
}
}

