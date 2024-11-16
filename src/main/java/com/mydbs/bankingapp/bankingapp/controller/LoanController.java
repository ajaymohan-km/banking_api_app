package com.mydbs.bankingapp.bankingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mydbs.bankingapp.bankingapp.model.LoanApplication;
import com.mydbs.bankingapp.bankingapp.model.LoanApplicationRequest;
import com.mydbs.bankingapp.bankingapp.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<LoanApplication> applyForLoan(@RequestBody LoanApplicationRequest request) {
        return ResponseEntity.ok(loanService.applyForLoan(request));
    }

    @GetMapping("/my-loans")
    public ResponseEntity<List<LoanApplication>> getMyLoans() {
        return ResponseEntity.ok(loanService.getCurrentUserLoans());
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanApplication> getLoanDetails(@PathVariable String loanId) {
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }
}