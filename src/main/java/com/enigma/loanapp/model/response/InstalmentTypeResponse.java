package com.enigma.loanapp.model.response;


import com.enigma.loanapp.util.enums.EInstalmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InstalmentTypeResponse {
    private String id;
    private EInstalmentType instalmentType;
}
