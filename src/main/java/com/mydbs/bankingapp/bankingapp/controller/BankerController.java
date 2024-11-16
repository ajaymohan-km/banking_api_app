package com.mydbs.bankingapp.bankingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mydbs.bankingapp.bankingapp.model.LoanApplication;
import com.mydbs.bankingapp.bankingapp.model.User;
import com.mydbs.bankingapp.bankingapp.service.LoanService;
import com.mydbs.bankingapp.bankingapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/banker")
@RequiredArgsConstructor
public class BankerController {
    private final LoanService loanService;
    private final UserService userService;

    @GetMapping("/customers")
    public ResponseEntity<List<User>> getAllCustomers() {
        return ResponseEntity.ok(userService.getUsersByRole("CUSTOMER"));
    }

    @GetMapping("/loans/pending")
    public ResponseEntity<List<LoanApplication>> getPendingLoans() {
        return ResponseEntity.ok(loanService.getPendingLoans());
    }

    @PutMapping("/loans/{loanId}/approve")
    public ResponseEntity<LoanApplication> approveLoan(@PathVariable String loanId) {
        return ResponseEntity.ok(loanService.approveLoan(loanId));
    }

    @PutMapping("/loans/{loanId}/reject")
    public ResponseEntity<LoanApplication> rejectLoan(@PathVariable String loanId) {
        return ResponseEntity.ok(loanService.rejectLoan(loanId));
    }
}