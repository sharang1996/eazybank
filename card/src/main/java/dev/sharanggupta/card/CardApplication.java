package dev.sharanggupta.card;

import dev.sharanggupta.card.dto.CardContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(value = CardContactInfoDto.class)
@SpringBootApplication
public class CardApplication {

  public static void main(String[] args) {
    SpringApplication.run(CardApplication.class, args);
  }
}
