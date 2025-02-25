package com.enigma.loanapp.service;

import com.enigma.loanapp.model.request.LoanTypeRequest;
import com.enigma.loanapp.model.response.LoanTypeResponse;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse create(LoanTypeRequest request);
    LoanTypeResponse getById(String id);
    List<LoanTypeResponse> getAll();
    LoanTypeResponse update(String id, LoanTypeRequest request);
    void delete(String id);
}
