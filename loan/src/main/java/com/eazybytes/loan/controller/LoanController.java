package com.eazybytes.loan.controller;

import com.eazybytes.loan.dto.ErrorResponseDto;
import com.eazybytes.loan.dto.LoanDto;
import com.eazybytes.loan.dto.ResponseDto;
import com.eazybytes.loan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD REST APIs for Loans in EazyBank",
    description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loans details")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

  private final LoanService loanService;

  @Operation(summary = "Create Loan REST API", description = "REST API to create new EazyBank Loan")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
    @ApiResponse(
        responseCode = "400",
        description = "HTTP Bad Request",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createLoan(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    loanService.createLoan(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            new ResponseDto(
                String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED.getReasonPhrase()));
  }

  @Operation(
      summary = "Fetch Loan Details REST API",
      description = "REST API to fetch loan details based on a mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Status Not Found",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @GetMapping("/fetch")
  public ResponseEntity<LoanDto> fetchLoan(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    return ResponseEntity.ok().body(loanService.fetchLoan(mobileNumber));
  }

  @Operation(
      summary = "Update Loan Details REST API",
      description = "REST API to update loan details")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
    @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto) {
    loanService.updateLoan(loanDto);
    return ResponseEntity.ok()
        .body(
            new ResponseDto(
                String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
  }

  @Operation(
      summary = "Delete loan REST API",
      description = "REST API to delete loan based on a mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Status Not Found",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteLoanDetails(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    loanService.deleteLoan(mobileNumber);
    return ResponseEntity.ok()
        .body(
            new ResponseDto(
                String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
  }
}
