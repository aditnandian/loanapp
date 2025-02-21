package com.enigma.loanapp.service;

import com.enigma.loanapp.model.request.TransactionRequest;
import com.enigma.loanapp.model.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest request);
}
