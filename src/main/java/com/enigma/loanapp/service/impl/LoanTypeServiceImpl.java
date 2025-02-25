package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.LoanType;
import com.enigma.loanapp.model.request.LoanTypeRequest;
import com.enigma.loanapp.model.response.LoanTypeResponse;
import com.enigma.loanapp.repository.LoanTypeRepository;
import com.enigma.loanapp.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

class DataNotFoundExceptionLoanType extends RuntimeException {
    public DataNotFoundExceptionLoanType(String message) {
        super(message);
    }
}

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {

    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanTypeResponse create(LoanTypeRequest request) {
        LoanType loanType = LoanType.builder()
                .type(request.getType())
                .maxLoan(request.getMaxLoan())
                .build();
        loanTypeRepository.save(loanType);
        return mapToResponse(loanType);
    }

    @Override
    public LoanTypeResponse getById(String id) {
        LoanType loanType = loanTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundExceptionLoanType("Loan Type not found"));
        return mapToResponse(loanType);
    }

    @Override
    public List<LoanTypeResponse> getAll() {
        return loanTypeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanTypeResponse update(String id, LoanTypeRequest request) {
        LoanType loanType = loanTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundExceptionLoanType("Loan Type not found"));

        loanType.setType(request.getType());
        loanType.setMaxLoan(request.getMaxLoan());

        loanTypeRepository.save(loanType);
        return mapToResponse(loanType);
    }

    @Override
    public void delete(String id) {
        if (!loanTypeRepository.existsById(id)) {
            throw new DataNotFoundExceptionLoanType("Loan Type not found");
        }
        loanTypeRepository.deleteById(id);
    }

    private LoanTypeResponse mapToResponse(LoanType loanType) {
        return LoanTypeResponse.builder()
                .id(loanType.getId())
                .type(loanType.getType())
                .maxLoan(loanType.getMaxLoan())
                .build();
    }
}
