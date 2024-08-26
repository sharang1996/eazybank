package com.eazybytes.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Loan", description = "Schema to hold Loan information")
public class LoanDto {

  @Schema(description = "Total Loan amount", example = "123456788761.05")
  double totalAmount;

  @Schema(description = "Amount paid back", example = "123456788761.05")
  double amountPaid;

  @Schema(description = "Mobile Number of the customer", example = "9234567890")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  private String mobileNumber;

  @Schema(description = "Loan Number of the customer", example = "123456788765")
  @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must be 12 digits")
  private String loanNumber;

  @Schema(description = "Type of the Loan", example = "HOME")
  @NotEmpty(message = "Loan type can not be a null or empty")
  @Size(min = 3, max = 30, message = "The length of the loan type should be between 3 and 30")
  private String loanType;
}
