package com.eazybytes.loan;

import com.eazybytes.loan.dto.LoanContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LoanContactInfoDto.class)
public class LoanApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoanApplication.class, args);
  }
}
