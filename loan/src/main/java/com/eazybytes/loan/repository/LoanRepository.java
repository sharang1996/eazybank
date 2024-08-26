package com.eazybytes.loan.repository;

import com.eazybytes.loan.entity.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  Optional<Loan> findByMobileNumber(String mobileNumber);
}
