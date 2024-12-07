package com.mydbs.bankingapp.bankingapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mydbs.bankingapp.bankingapp.model.Account;
import com.mydbs.bankingapp.bankingapp.model.AccountCreationRequest;
import com.mydbs.bankingapp.bankingapp.model.AccountLinkRequest;
import com.mydbs.bankingapp.bankingapp.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreationRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    
    @PostMapping("/link")
    public ResponseEntity<Account> linkExistingAccount(@RequestBody AccountLinkRequest request) {
        return ResponseEntity.ok(accountService.linkExistingAccount(request));
    }

}
