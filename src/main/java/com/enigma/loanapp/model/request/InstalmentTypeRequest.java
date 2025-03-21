package com.enigma.loanapp.model.request;


import com.enigma.loanapp.util.enums.EInstalmentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InstalmentTypeRequest {
    @NotNull(message = "Instalment type cannot be blank")
    private EInstalmentType instalmentType;
}
