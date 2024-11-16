package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CustomerActivity {
    private String customerId;
    private String customerName;
    private String activityType;
    private String description;
    private LocalDateTime timestamp;
    private String status;
    private String location;
    private String deviceInfo;
}