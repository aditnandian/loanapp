package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.Role;
import com.enigma.loanapp.repository.RoleRepository;
import com.enigma.loanapp.service.RoleService;
import com.enigma.loanapp.util.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> newRole = repository.findByRole(role);

        return newRole.orElseGet(() -> repository.saveAndFlush(
                Role.builder()
                        .role(role)
                        .build()
        ));
    }
}