package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.Transaction;
import com.mydbs.bankingapp.bankingapp.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }

    public Transaction getTransactionById(String transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public List<Transaction> getTransactionsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByTimestampBetween(startDate, endDate);
    }
}