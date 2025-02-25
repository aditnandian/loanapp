package com.enigma.loanapp.service;

import com.enigma.loanapp.entity.InstalmentType;
import com.enigma.loanapp.model.request.InstalmentTypeRequest;
import com.enigma.loanapp.model.request.UpdateInstalmentTypeRequest;
import com.enigma.loanapp.model.response.InstalmentTypeResponse;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentType create(InstalmentTypeRequest request);
    InstalmentType getInstalmentTypeById(String id);
    List<InstalmentTypeResponse> getAll();
    InstalmentTypeResponse update(UpdateInstalmentTypeRequest request);
    void delete(String id);
}
