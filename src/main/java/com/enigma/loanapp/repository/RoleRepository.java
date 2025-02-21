package com.enigma.loanapp.repository;

import com.enigma.loanapp.entity.Role;
import com.enigma.loanapp.util.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}