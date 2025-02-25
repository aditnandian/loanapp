package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.InstalmentType;
import com.enigma.loanapp.model.request.InstalmentTypeRequest;
import com.enigma.loanapp.model.request.UpdateInstalmentTypeRequest;
import com.enigma.loanapp.model.response.InstalmentTypeResponse;
import com.enigma.loanapp.repository.InstalmentTypeRepository;
import com.enigma.loanapp.service.InstalmentTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {

    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentType create(InstalmentTypeRequest request) {
        InstalmentType instalmentType = InstalmentType.builder()
                .instalmentType(request.getInstalmentType())
                .build();

        return instalmentTypeRepository.save(instalmentType);
    }

    @Override
    public InstalmentType getInstalmentTypeById(String id) {
        return instalmentTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Instalment Type not found"));
    }

    @Override
    public List<InstalmentTypeResponse> getAll() {
        List<InstalmentType> instalmentTypes = instalmentTypeRepository.findAll();
        return instalmentTypes.stream().map(this::mapToRes).toList();
    }

    @Override
    public InstalmentTypeResponse update(UpdateInstalmentTypeRequest request) {
        InstalmentType instalmentType = getInstalmentTypeById(request.getId());
        instalmentType.setInstalmentType(request.getInstalmentType());

        return mapToRes(instalmentTypeRepository.save(instalmentType));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        InstalmentType instalmentType = getInstalmentTypeById(id);
        instalmentTypeRepository.delete(instalmentType);
    }

    private InstalmentTypeResponse mapToRes(InstalmentType instalmentType) {
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType())
                .build();
    }
}
