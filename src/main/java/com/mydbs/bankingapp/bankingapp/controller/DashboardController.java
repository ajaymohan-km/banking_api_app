package com.mydbs.bankingapp.bankingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mydbs.bankingapp.bankingapp.model.AdminDashboard;
import com.mydbs.bankingapp.bankingapp.model.BankerDashboard;
import com.mydbs.bankingapp.bankingapp.model.CustomerDashboard;
import com.mydbs.bankingapp.bankingapp.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/customer")
    public ResponseEntity<CustomerDashboard> getCustomerDashboard() {
        return ResponseEntity.ok(dashboardService.getCustomerDashboard());
    }

    @GetMapping("/banker")
    public ResponseEntity<BankerDashboard> getBankerDashboard() {
        return ResponseEntity.ok(dashboardService.getBankerDashboard());
    }

    @GetMapping("/admin")
    public ResponseEntity<AdminDashboard> getAdminDashboard() {
        return ResponseEntity.ok(dashboardService.getAdminDashboard());
    }
}