package com.enigma.loanapp.repository;

import com.enigma.loanapp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findAppUserByEmail(String email);
}