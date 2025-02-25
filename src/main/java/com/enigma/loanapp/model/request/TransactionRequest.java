package com.enigma.loanapp.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {
    @NotBlank(message = "Loan Type ID cannot be blank")
    private String loanTypeId;

    @NotBlank(message = "Instalment Type ID cannot be blank")
    private String instalmentTypeId;

    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;

    @NotNull(message = "Nominal cannot be null")
    private Double nominal;
}
