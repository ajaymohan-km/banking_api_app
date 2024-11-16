package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.util.Map;

@Data
public class UserStats {
    private int totalUsers;
    private int activeCustomers;
    private int activeBankers;
    private int activeAdmins;
    private Map<String, Integer> usersByRole;
    private int newUsersToday;
    private int newUsersThisWeek;
    private int newUsersThisMonth;
    private Map<String, Integer> usersByStatus;
    private double userGrowthRate;
}