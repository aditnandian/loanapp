package com.enigma.loanapp.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailResponse {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private String loanStatus;
    private Long createdAt;
    private Long updatedAt;
}
