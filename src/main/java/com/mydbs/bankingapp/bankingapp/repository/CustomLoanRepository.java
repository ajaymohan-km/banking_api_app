package com.mydbs.bankingapp.bankingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mydbs.bankingapp.bankingapp.model.LoanApplication;

import java.util.List;

@Repository
public interface CustomLoanRepository extends MongoRepository<LoanApplication, String> {
    @Query("{'status': 'PENDING', 'amount': {$gte: ?0}}")
    List<LoanApplication> findLargeLoans(double amount);
    
    @Query("{'userId': ?0, 'status': {$in: ['APPROVED', 'PENDING']}}")
    List<LoanApplication> findActiveLoans(String userId);
}