package com.enigma.loanapp.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTypeRequest {
    private String type;
    private Double maxLoan;
}
