package com.eazybytes.card.controller;

import com.eazybytes.card.dto.CardContactInfoDto;
import com.eazybytes.card.dto.CardDto;
import com.eazybytes.card.dto.ErrorResponseDto;
import com.eazybytes.card.dto.ResponseDto;
import com.eazybytes.card.service.CardService;
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
    name = "CRUD REST APIs for Cards in EazyBank",
    description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

  private final CardService cardService;
  private final CardContactInfoDto cardContactInfoDto;

  @Operation(summary = "Create Card REST API", description = "REST API to create new EazyBank Card")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
    @ApiResponse(
        responseCode = "400",
        description = "HTTP Bad Request",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createCard(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    cardService.createCard(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            new ResponseDto(
                String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED.getReasonPhrase()));
  }

  @Operation(
      summary = "Fetch Card Details REST API",
      description = "REST API to fetch card details based on a mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Status Not Found",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @GetMapping("/fetch")
  public ResponseEntity<CardDto> fetchCard(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    return ResponseEntity.ok().body(cardService.fetchCard(mobileNumber));
  }

  @Operation(
      summary = "Update Card Details REST API",
      description = "REST API to update card details")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
    @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardDto) {
    cardService.updateCard(cardDto);
    return ResponseEntity.ok()
        .body(
            new ResponseDto(
                String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
  }

  @Operation(
      summary = "Delete card REST API",
      description = "REST API to delete card based on a mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Status Not Found",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteCardDetails(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    cardService.deleteCard(mobileNumber);
    return ResponseEntity.ok()
        .body(
            new ResponseDto(
                String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()));
  }

  @Operation(
          summary = "Fetch Contact Information REST API",
          description = "REST API to fetch Contact Information")
  @ApiResponse(responseCode = "200", description = "HTTP Status OK")
  @GetMapping("/contact-info")
  public ResponseEntity<CardContactInfoDto> getContactInfo() {
    return ResponseEntity.ok().body(cardContactInfoDto);
  }
}
