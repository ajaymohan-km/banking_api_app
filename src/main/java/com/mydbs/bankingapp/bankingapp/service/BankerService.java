package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.BankerRegistrationRequest;
import com.mydbs.bankingapp.bankingapp.model.User;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class BankerService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public User registerBanker(BankerRegistrationRequest request) {
        User banker = new User();
        banker.setUsername(request.getUsername());
        banker.setPassword(passwordEncoder.encode(request.getPassword()));
        banker.setEmail(request.getEmail());
        banker.setFullName(request.getFullName());
        banker.setRoles(Collections.singleton("BANKER"));
        return userService.saveUser(banker);
    }
}