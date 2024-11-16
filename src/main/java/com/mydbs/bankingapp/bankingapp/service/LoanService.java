package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.LoanApplication;
import com.mydbs.bankingapp.bankingapp.model.LoanApplicationRequest;
import com.mydbs.bankingapp.bankingapp.model.LoanApplicationSummary;
import com.mydbs.bankingapp.bankingapp.model.LoanSummary;
import com.mydbs.bankingapp.bankingapp.repository.LoanApplicationRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanApplicationRepository loanRepository;
    private final UserService userService;

    public LoanApplication applyForLoan(LoanApplicationRequest request) {
        LoanApplication loan = new LoanApplication();
        loan.setUserId(userService.getCurrentUserId());
        loan.setAmount(request.getAmount());
        loan.setStatus("PENDING");
        loan.setPurpose(request.getPurpose());
        loan.setMonthlyIncome(request.getMonthlyIncome());
        loan.setEmploymentStatus(request.getEmploymentStatus());
        return loanRepository.save(loan);
    }

    public List<LoanApplication> getCurrentUserLoans() {
        return loanRepository.findByUserId(userService.getCurrentUserId());
    }

    public List<LoanApplication> getPendingLoans() {
        return loanRepository.findByStatus("PENDING");
    }

    public LoanApplication getLoanById(String loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public LoanApplication approveLoan(String loanId) {
        LoanApplication loan = getLoanById(loanId);
        loan.setStatus("APPROVED");
        return loanRepository.save(loan);
    }

    public LoanApplication rejectLoan(String loanId) {
        LoanApplication loan = getLoanById(loanId);
        loan.setStatus("REJECTED");
        return loanRepository.save(loan);
    }

    public List<LoanSummary> getActiveLoanSummaries(String userId) {
        return loanRepository.findByUserIdAndStatus(userId, "APPROVED")
                .stream()
                .map(this::convertToLoanSummary)
                .collect(Collectors.toList());
    }

    public int countPendingApplications() {
        return loanRepository.countByStatus("PENDING");
    }

    public List<LoanApplicationSummary> getRecentApplications(int limit) {
        return loanRepository.findByOrderByApplicationDateDesc(PageRequest.of(0, limit))
                .stream()
                .map(this::convertToLoanApplicationSummary)
                .collect(Collectors.toList());
    }

    private LoanSummary convertToLoanSummary(LoanApplication loan) {
        LoanSummary summary = new LoanSummary();
        summary.setLoanId(loan.getId());
        summary.setPrincipalAmount(loan.getAmount());
        summary.setRemainingAmount(loan.getAmount()); 
        summary.setStatus(loan.getStatus());
        summary.setStartDate(loan.getApplicationDate().toLocalDate());
       
        return summary;
    }

    private LoanApplicationSummary convertToLoanApplicationSummary(LoanApplication loan) {
        LoanApplicationSummary summary = new LoanApplicationSummary();
        summary.setApplicationId(loan.getId());
        summary.setCustomerId(loan.getUserId());
        summary.setRequestedAmount(loan.getAmount());
        summary.setStatus(loan.getStatus());
        summary.setApplicationDate(loan.getApplicationDate());
        return summary;
    }

    
}