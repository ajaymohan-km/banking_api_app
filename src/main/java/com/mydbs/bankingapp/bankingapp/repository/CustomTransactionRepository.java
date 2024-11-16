package com.mydbs.bankingapp.bankingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mydbs.bankingapp.bankingapp.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomTransactionRepository extends MongoRepository<Transaction, String> {
    @Query("{'timestamp': {$gte: ?0, $lte: ?1}}")
    List<Transaction> findTransactionsInDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("{'amount': {$gte: ?0}}")
    List<Transaction> findLargeTransactions(double amount);
}