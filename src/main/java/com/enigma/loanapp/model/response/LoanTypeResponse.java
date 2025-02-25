package com.enigma.loanapp.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTypeResponse {
    private String id;
    private String type;
    private Double maxLoan;
}
