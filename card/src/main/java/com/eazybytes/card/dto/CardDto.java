package com.eazybytes.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Card", description = "Schema to hold Card information")
public class CardDto {

  @Schema(description = "Card limit", example = "123456788761.05")
  double totalLimit;
  @Schema(description = "Amount Used", example = "123456788761.05")
  double amountUsed;
  @Schema(description = "Mobile Number of the customer", example = "9234567890")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  private String mobileNumber;
  @Schema(description = "Card Number of the customer", example = "1234567887654321")
  @Pattern(regexp = "(^$|[0-9]{16})", message = "Card number must be 16 digits")
  private String cardNumber;
  @Schema(description = "Type of the card", example = "VISA")
  @NotEmpty(message = "Card type can not be a null or empty")
  @Size(min = 5, max = 30, message = "The length of the card type should be between 5 and 30")
  private String cardType;
}
