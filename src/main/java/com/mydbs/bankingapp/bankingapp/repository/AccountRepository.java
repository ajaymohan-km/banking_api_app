package com.mydbs.bankingapp.bankingapp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mydbs.bankingapp.bankingapp.model.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findByUserId(String userId);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByUserIdAndActive(String userId, boolean active);
    boolean existsByAccountNumber(String accountNumber);
}