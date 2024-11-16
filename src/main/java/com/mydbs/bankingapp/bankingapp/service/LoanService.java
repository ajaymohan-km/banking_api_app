package com.mydbs.bankingapp.bankingapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mydbs.bankingapp.bankingapp.model.LoanApplication;
import com.mydbs.bankingapp.bankingapp.model.LoanApplicationRequest;
import com.mydbs.bankingapp.bankingapp.repository.LoanApplicationRepository;

import java.util.List;

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
}