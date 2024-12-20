package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.BankerRegistrationRequest;
import com.mydbs.bankingapp.bankingapp.model.User;
import com.mydbs.bankingapp.bankingapp.model.UserStats;
import com.mydbs.bankingapp.bankingapp.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public String getCurrentUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRoles(role);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }

        public User registerBanker(BankerRegistrationRequest request) {
        User banker = new User();
        banker.setUsername(request.getUsername());
        banker.setPassword(passwordEncoder.encode(request.getPassword()));
        banker.setEmail(request.getEmail());
        banker.setFullName(request.getFullName());
        banker.setRoles(Collections.singleton("BANKER"));
        return saveUser(banker);
    }

        public int countCustomers() {
        return userRepository.countByRoles("CUSTOMER");
    }

    public UserStats collectUserStats() {
        UserStats stats = new UserStats();
        stats.setTotalUsers((int) userRepository.count());
        stats.setActiveCustomers(countCustomers());
        stats.setActiveBankers(userRepository.countByRoles("BANKER"));
        stats.setActiveAdmins(userRepository.countByRoles("ADMIN"));
        return stats;
    }

}