package com.enigma.loanapp.repository;

import com.enigma.loanapp.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String>, JpaSpecificationExecutor<InstalmentType> {
}
