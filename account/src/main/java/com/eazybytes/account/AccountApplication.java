package com.eazybytes.account;

import com.eazybytes.account.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = AccountContactInfoDto.class)
@OpenAPIDefinition(
    info =
        @Info(
            title = "Accounts microservice REST API Documentation",
            description = "EazyBank Accounts microservice REST API Documentation",
            version = "v1",
            contact =
                @Contact(
                    name = "Sharang Gupta",
                    email = "sharanggupta96@gmail.com",
                    url = "https://www.sharanggupta.dev"),
            license = @License(name = "Apache 2.0")),
    externalDocs =
        @ExternalDocumentation(
            description = "EazyBank Accounts microservice REST API Documentation",
            url = "https://github.com/sharang1996/microservices/tree/main/accounts"))
public class AccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplication.class, args);
  }
}
