package com.mydbs.bankingapp.bankingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mydbs.bankingapp.bankingapp.model.BankerRegistrationRequest;
import com.mydbs.bankingapp.bankingapp.model.Transaction;
import com.mydbs.bankingapp.bankingapp.model.User;
import com.mydbs.bankingapp.bankingapp.service.TransactionService;
import com.mydbs.bankingapp.bankingapp.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final TransactionService transactionService;

    @PostMapping("/bankers")
    public ResponseEntity<User> registerBanker(@RequestBody BankerRegistrationRequest request) {
        // Implementation for registering new bankers
        return ResponseEntity.ok(userService.registerBanker(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(transactionService.getTransactionsBetweenDates(startDate, endDate));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}