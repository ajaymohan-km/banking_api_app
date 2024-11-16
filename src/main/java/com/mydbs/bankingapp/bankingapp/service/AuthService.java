package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.AuthRequest;
import com.mydbs.bankingapp.bankingapp.model.AuthResponse;
import com.mydbs.bankingapp.bankingapp.model.RegisterRequest;
import com.mydbs.bankingapp.bankingapp.model.User;
import com.mydbs.bankingapp.bankingapp.repository.UserRepository;
import com.mydbs.bankingapp.bankingapp.util.JwtUtil;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setRoles(Collections.singleton(request.getRole()));
        
        userRepository.save(user);
        
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getUsername(), user.getRoles().iterator().next(), "Registration successful");
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getUsername(), user.getRoles().iterator().next(), "Login successful");
    }
}