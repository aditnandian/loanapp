package com.enigma.loanapp.service;

import com.enigma.loanapp.entity.InstalmentType;;
import com.enigma.loanapp.model.request.InstalmentTypeRequest;
import com.enigma.loanapp.model.response.InstalmentTypeResponse;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentType create(InstalmentTypeRequest request);
    InstalmentType getInstalmentTypeById(String id);
    List<InstalmentTypeResponse> getAll();
    void delete(String id);
}
