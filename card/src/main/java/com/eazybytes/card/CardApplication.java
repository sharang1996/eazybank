package com.eazybytes.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CardApplication {

  public static void main(String[] args) {
    SpringApplication.run(CardApplication.class, args);
  }
}
