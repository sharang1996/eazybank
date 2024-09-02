package dev.sharanggupta.account.service;

import dev.sharanggupta.account.client.CardFeignClient;
import dev.sharanggupta.account.client.LoanFeignClient;
import dev.sharanggupta.account.dto.AccountDto;
import dev.sharanggupta.account.dto.CardDto;
import dev.sharanggupta.account.dto.CustomerDetailsDto;
import dev.sharanggupta.account.dto.LoanDto;
import dev.sharanggupta.account.entity.Account;
import dev.sharanggupta.account.entity.Customer;
import dev.sharanggupta.account.exception.ResourceNotFoundException;
import dev.sharanggupta.account.mapper.AccountMapper;
import dev.sharanggupta.account.mapper.CustomerMapper;
import dev.sharanggupta.account.repository.AccountRepository;
import dev.sharanggupta.account.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private AccountRepository accountRepository;
  private CustomerRepository customerRepository;
  private CardFeignClient cardFeignClient;
  private LoanFeignClient loanFeignClient;

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Customer Details based on a given mobileNumber
   */
  @Override
  public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
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

    CustomerDetailsDto customerDetailsDto =
        CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
    customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

    ResponseEntity<LoanDto> loansDtoResponseEntity = loanFeignClient.fetchLoan(mobileNumber);
    customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

    ResponseEntity<CardDto> cardsDtoResponseEntity = cardFeignClient.fetchCard(mobileNumber);
    customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

    return customerDetailsDto;
  }
}
