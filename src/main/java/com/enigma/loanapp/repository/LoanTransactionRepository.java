package com.enigma.loanapp.repository;

import com.enigma.loanapp.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, String>, JpaSpecificationExecutor<LoanTransaction> {
}
