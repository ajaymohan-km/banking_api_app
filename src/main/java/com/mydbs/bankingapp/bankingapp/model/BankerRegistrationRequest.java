package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class BankerRegistrationRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;
    @NotBlank
    private String fullName;
    private String employeeId;
    private String branch;
}