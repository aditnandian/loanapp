package com.enigma.loanapp.repository;

import com.enigma.loanapp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}