package dev.sharanggupta.account.controller;

import dev.sharanggupta.account.dto.*;
import dev.sharanggupta.account.service.AccountService;
import dev.sharanggupta.account.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
    name = "CRUD REST APIs for Customers in EazyBank",
    description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE customer details")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/api/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

  private final CustomerService customerService;

  @Operation(
          summary = "Fetch Customer Details REST API",
          description = "REST API to fetch Customer details based on a mobile number")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
          @ApiResponse(
                  responseCode = "404",
                  description = "HTTP Status Not Found",
                  content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @GetMapping("/fetch")
  public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId,
          @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                 String mobileNumber) {
    log.debug("correlationId: {}", correlationId);
    CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber, correlationId);
    return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
  }
}
