package com.mydbs.bankingapp.bankingapp.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class TransactionStats {
    private BigDecimal totalTransactionVolume;
    private int totalTransactionCount;
    private Map<String, BigDecimal> volumeByTransactionType;
    private Map<String, Integer> countByTransactionType;
    private BigDecimal averageTransactionSize;
    private Map<String, Double> successRateByType;
    private Map<String, BigDecimal> revenueByService;
    private double fraudDetectionRate;
}