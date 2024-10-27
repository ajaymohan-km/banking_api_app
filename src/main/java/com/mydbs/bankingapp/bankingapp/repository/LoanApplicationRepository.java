package com.mydbs.bankingapp.bankingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mydbs.bankingapp.bankingapp.model.LoanApplication;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends MongoRepository<LoanApplication, String> {
    List<LoanApplication> findByUserId(String userId);
    List<LoanApplication> findByStatus(String status);
    List<LoanApplication> findByUserIdAndStatus(String userId, String status);
}