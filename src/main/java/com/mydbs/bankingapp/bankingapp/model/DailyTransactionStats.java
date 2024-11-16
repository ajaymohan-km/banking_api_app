package com.mydbs.bankingapp.bankingapp.model;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class DailyTransactionStats {
    private int totalTransactions;
    private BigDecimal totalAmount;
    private Map<String, Integer> transactionsByType;
    private Map<String, BigDecimal> amountByType;
    private int successfulTransactions;
    private int failedTransactions;
    private BigDecimal averageTransactionAmount;
    private Map<Integer, Integer> transactionsByHour;
    private String peakTransactionHour;
    private BigDecimal highestTransaction;
}