package dev.sharanggupta.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAccountMismatchException extends RuntimeException {
  public CustomerAccountMismatchException(String message) {
    super(message);
  }
}
