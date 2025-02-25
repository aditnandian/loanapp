package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.entity.InstalmentType;
import com.enigma.loanapp.entity.LoanTransaction;
import com.enigma.loanapp.entity.LoanType;
import com.enigma.loanapp.model.request.ApproveTransactionRequest;
import com.enigma.loanapp.model.request.TransactionRequest;
import com.enigma.loanapp.model.response.TransactionResponse;
import com.enigma.loanapp.repository.CustomerRepository;
import com.enigma.loanapp.repository.InstalmentTypeRepository;
import com.enigma.loanapp.repository.LoanTransactionRepository;
import com.enigma.loanapp.repository.LoanTypeRepository;
import com.enigma.loanapp.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final LoanTransactionRepository transactionRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final InstalmentTypeRepository instalmentTypeRepository;
    private final CustomerRepository customerRepository;


    @Transactional(rollbackOn = Exception.class)
    public TransactionResponse createTransaction(TransactionRequest request) {
        LoanType loanType = loanTypeRepository.findById(request.getLoanTypeId())
                .orElseThrow(() -> new RuntimeException("Loan Type not found"));

        InstalmentType instalmentType = instalmentTypeRepository.findById(request.getInstalmentTypeId())
                .orElseThrow(() -> new RuntimeException("Instalment Type not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        LoanTransaction transaction = LoanTransaction.builder()
                .loanType(loanType)
                .instalmentType(instalmentType)
                .customer(customer)
                .nominal(request.getNominal())
                .approvalStatus("PENDING")
                .createdAt(new Date())
                .build();

        LoanTransaction savedTransaction = transactionRepository.save(transaction);
        return mapToResponse(savedTransaction);
    }

    @Override
    public TransactionResponse getTransactionById(String id) {
        LoanTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction Not Found"));
        return mapToResponse(transaction);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransactionResponse approveTransaction(String adminId, ApproveTransactionRequest request) {
        LoanTransaction transaction = transactionRepository.findById(request.getLoanTransactionId())
                .orElseThrow(() -> new RuntimeException("Transaction Not Found"));

        transaction.setApprovedBy(adminId);
        transaction.setApprovedAt(new Date());
        transaction.setApprovalStatus("APPROVED");
        transaction.setUpdatedAt(new Date());

        LoanTransaction updatedTransaction = transactionRepository.save(transaction);
        return mapToResponse(updatedTransaction);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void payInstallment(String trxId) {
        LoanTransaction transaction = transactionRepository.findById(trxId)
                .orElseThrow(() -> new RuntimeException("Transaction Not Found"));

        transaction.setApprovalStatus("PAID");
        transaction.setUpdatedAt(new Date());
        transactionRepository.save(transaction);
    }

    private TransactionResponse mapToResponse(LoanTransaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .loanTypeId(transaction.getLoanType().getId())
                .instalmentTypeId(transaction.getInstalmentType().getId())
                .customerId(transaction.getCustomer().getId())
                .nominal(transaction.getNominal())
                .approvedAt(transaction.getApprovedAt().getTime())
                .approvedBy(transaction.getApprovedBy())
                .approvalStatus(transaction.getApprovalStatus())
                .createdAt(transaction.getCreatedAt().getTime())
                .updatedAt(transaction.getUpdatedAt().getTime())
                .build();
    }
}
