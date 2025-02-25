package com.enigma.loanapp.controller;

import com.enigma.loanapp.model.request.TransactionRequest;
import com.enigma.loanapp.model.request.ApproveTransactionRequest;
import com.enigma.loanapp.model.response.CommonResponse;
import com.enigma.loanapp.model.response.TransactionResponse;
import com.enigma.loanapp.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CommonResponse<TransactionResponse>> createTransaction(@Valid @RequestBody TransactionRequest request) {
        TransactionResponse transaction = transactionService.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<TransactionResponse>builder()
                        .message("Transaction created successfully")
                        .data(transaction)
                        .build());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN', 'STAFF')")
    public ResponseEntity<CommonResponse<TransactionResponse>> getTransactionById(@PathVariable String id) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(CommonResponse.<TransactionResponse>builder()
                .message("Transaction retrieved successfully")
                .data(transaction)
                .build());
    }

    @PutMapping("/{adminId}/approve")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<CommonResponse<TransactionResponse>> approveTransaction(
            @PathVariable String adminId,
            @Valid @RequestBody ApproveTransactionRequest request) {
        TransactionResponse transaction = transactionService.approveTransaction(adminId, request);
        return ResponseEntity.ok(CommonResponse.<TransactionResponse>builder()
                .message("Transaction approved successfully")
                .data(transaction)
                .build());
    }

    @PutMapping("/{trxId}/pay")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CommonResponse<Void>> payInstallment(@PathVariable String trxId) {
        transactionService.payInstallment(trxId);
        return ResponseEntity.ok(CommonResponse.<Void>builder()
                .message("Installment paid successfully")
                .data(null)
                .build());
    }
}
