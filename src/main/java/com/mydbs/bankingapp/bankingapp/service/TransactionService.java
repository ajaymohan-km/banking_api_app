package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.Transaction;
import com.mydbs.bankingapp.bankingapp.model.TransactionStats;
import com.mydbs.bankingapp.bankingapp.model.TransactionSummary;
import com.mydbs.bankingapp.bankingapp.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public List<TransactionSummary> getRecentTransactions(String userId, int limit) {
        return transactionRepository.findByUserId(userId)
            .stream()
            .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
            .limit(limit)
            .map(transaction -> {
                TransactionSummary summary = new TransactionSummary();
                summary.setTransactionId(transaction.getId());
                summary.setAmount(transaction.getAmount());
                summary.setTimestamp(transaction.getTimestamp());
                return summary;
            })
            .collect(Collectors.toList());
    }
    

    public TransactionStats collectTransactionStats() {
        TransactionStats stats = new TransactionStats();
        stats.setTotalTransactionCount((int) transactionRepository.count());

        return stats;
    }
}