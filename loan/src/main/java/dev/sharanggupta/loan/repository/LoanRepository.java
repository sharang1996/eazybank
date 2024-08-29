package dev.sharanggupta.loan.repository;

import dev.sharanggupta.loan.entity.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  Optional<Loan> findByMobileNumber(String mobileNumber);
}
