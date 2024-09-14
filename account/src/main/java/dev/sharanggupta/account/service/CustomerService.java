package dev.sharanggupta.account.service;

import dev.sharanggupta.account.dto.CustomerDetailsDto;

public interface CustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
