package dev.sharanggupta.loan.service;

import dev.sharanggupta.loan.dto.LoanDto;
import dev.sharanggupta.loan.entity.Loan;
import dev.sharanggupta.loan.exception.InternalServerException;
import dev.sharanggupta.loan.exception.LoanAlreadyExistsException;
import dev.sharanggupta.loan.exception.ResourceNotFoundException;
import dev.sharanggupta.loan.mapper.LoanMapper;
import dev.sharanggupta.loan.repository.LoanRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {
  private final LoanRepository loanRepository;

  @Override
  public void createLoan(String mobileNumber) {
    Optional<Loan> existingLoan = loanRepository.findByMobileNumber(mobileNumber);
    if (existingLoan.isPresent()) {
      throw new LoanAlreadyExistsException(
          "Loan already registered with given mobileNumber " + mobileNumber);
    }
    loanRepository.save(createNewLoan(mobileNumber));
  }

  @Override
  public LoanDto fetchLoan(String mobileNumber) {
    Loan loan =
        loanRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
    return LoanMapper.mapToLoanDto(loan, new LoanDto());
  }

  @Override
  public void updateLoan(LoanDto loanDto) {
    String mobileNumber = loanDto.getMobileNumber();
    Loan currentLoan =
        loanRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Loan", "MobileNumber", mobileNumber));
    Loan updatedLoan = LoanMapper.mapToLoan(loanDto, new Loan());
    updatedLoan.setLoanId(currentLoan.getLoanId());
    loanRepository.save(updatedLoan);
  }

  @Override
  public void deleteLoan(String mobileNumber) {
    Loan loan =
        loanRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
    try {
      loanRepository.deleteById(loan.getLoanId());
    } catch (DataAccessException exception) {
      throw new InternalServerException(exception.getCause(), exception.getMessage());
    }
  }

  private Loan createNewLoan(String mobileNumber) {
    Loan newLoan = new Loan();
    long randomLoanNumber = (long) (Math.random() * 1000000000000L);
    newLoan.setLoanNumber(Long.toString(randomLoanNumber));
    newLoan.setMobileNumber(mobileNumber);
    newLoan.setLoanType("Personal");
    newLoan.setTotalAmount(1_00_000);
    newLoan.setAmountPaid(0);
    return newLoan;
  }
}
