package com.mydbs.bankingapp.bankingapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mydbs.bankingapp.bankingapp.model.Transaction;
import com.mydbs.bankingapp.bankingapp.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionDetails(@PathVariable String transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }
}