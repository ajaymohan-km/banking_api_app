package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private String id;
    private String message;
    private String type;  // ALERT, INFO, WARNING
    private boolean read;
    private LocalDateTime timestamp;
    private String priority; // HIGH, MEDIUM, LOW
    private String actionUrl;
}