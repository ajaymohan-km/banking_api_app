package com.mydbs.bankingapp.bankingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mydbs.bankingapp.bankingapp.model.User;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByRoles(String role);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    int countByRoles(String role);
}