package com.enigma.loanapp.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApproveTransactionRequest {
    private String loanTransactionId;
    private Integer interestRates;
}
