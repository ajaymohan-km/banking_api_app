package com.mydbs.bankingapp.bankingapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mydbs.bankingapp.bankingapp.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByFromAccountId(String accountId);
    List<Transaction> findByToAccountId(String accountId);
    List<Transaction> findByFromAccountIdOrToAccountId(String fromAccountId, String toAccountId);
    List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Transaction> findByUserIdOrderByTimestampDesc(String userId, Pageable pageable);
}