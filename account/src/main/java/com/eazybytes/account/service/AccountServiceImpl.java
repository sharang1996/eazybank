package com.eazybytes.account.service;

import com.eazybytes.account.dto.AccountDto;
import com.eazybytes.account.dto.CustomerDto;
import com.eazybytes.account.entity.Account;
import com.eazybytes.account.entity.Customer;
import com.eazybytes.account.exception.*;
import com.eazybytes.account.mapper.AccountMapper;
import com.eazybytes.account.mapper.CustomerMapper;
import com.eazybytes.account.repository.AccountRepository;
import com.eazybytes.account.repository.CustomerRepository;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  public static final String SAVINGS = "Savings";
  public static final String BRANCH_ADDRESS = "123 Main Street, New York";

  private static final Random RANDOM = new Random();
  private final AccountRepository accountRepository;
  private final CustomerRepository customerRepository;

  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> existingCustomer =
        customerRepository.findByMobileNumber(customer.getMobileNumber());
    if (existingCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
    }

    Customer savedCustomer = customerRepository.save(customer);
    accountRepository.save(createNewAccount(savedCustomer));
  }

  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    Account account =
        accountRepository
            .findByCustomerId(customer.getCustomerId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Account", "customerId", customer.getCustomerId().toString()));

    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));
    return customerDto;
  }

  @Override
  public void updateAccount(CustomerDto customerDto) {
    AccountDto accountDto = customerDto.getAccountDto();
    if (accountDto == null) {
      throw new AccountDetailsNotPopulatedException(
          String.format(
              "Account details not populated for customer %s with mobile number %s",
              customerDto.getName(), customerDto.getMobileNumber()));
    }
    Account account = retrieveExistingAccount(accountDto);
    Customer customer = retrieveExistingCustomer(customerDto);
    if (customer.getCustomerId().longValue() != account.getCustomerId().longValue()) {
      throw new CustomerAccountMismatchException(
          String.format(
              "Account number %s provided do not match with customer records for customer %s with mobile number %s",
              account.getAccountNumber(), customerDto.getName(), customerDto.getMobileNumber()));
    }
    AccountMapper.mapToAccount(accountDto, account);
    accountRepository.save(account);

    updateCustomer(customerDto, customer);
  }

  @Override
  public void deleteAccount(String mobileNumber) {
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    try {
      accountRepository.deleteByCustomerId(customer.getCustomerId());
      customerRepository.deleteById(customer.getCustomerId());
    } catch (DataAccessException exception) {
      throw new InternalServerException(exception.getCause(), exception.getMessage());
    }
  }

  private void updateCustomer(CustomerDto customerDto, Customer customer) {
    CustomerMapper.mapToCustomer(customerDto, customer);
    customerRepository.save(customer);
  }

  private Account retrieveExistingAccount(AccountDto accountDto) {
    Long accountNumber = accountDto.getAccountNumber();
    return accountRepository
        .findById(accountNumber)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Account", "AccountNumber", accountNumber.toString()));
  }

  private Customer retrieveExistingCustomer(CustomerDto customerDto) {
    String mobileNumber = customerDto.getMobileNumber();
    return customerRepository
        .findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
  }

  private Account createNewAccount(Customer customer) {
    Account newAccount = new Account();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + RANDOM.nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(SAVINGS);
    newAccount.setBranchAddress(BRANCH_ADDRESS);

    return newAccount;
  }
}
