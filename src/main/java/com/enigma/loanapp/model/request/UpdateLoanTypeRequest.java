package com.enigma.loanapp.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLoanTypeRequest {
    
    @NotBlank(message = "Loan type ID cannot be blank")
    private String id;

    @NotBlank(message = "Loan type cannot be blank")
    private String type;

    @NotNull(message = "Max loan amount cannot be null")
    @Min(value = 1, message = "Max loan must be at least 1")
    private Double maxLoan;
}
