package com.eazybytes.loan.service;

import com.eazybytes.loan.dto.LoanDto;

public interface LoanService {

  void createLoan(String mobileNumber);

  LoanDto fetchLoan(String mobileNumber);

  void updateLoan(LoanDto loanDto);

  void deleteLoan(String mobileNumber);
}
