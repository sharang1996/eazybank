package dev.sharanggupta.account.service;

import dev.sharanggupta.account.dto.CustomerDto;

public interface AccountService {

  void createAccount(CustomerDto customerDto);

  CustomerDto fetchAccount(String mobileNumber);

  void updateAccount(CustomerDto customerDto);

  void deleteAccount(String mobileNumber);
}
