package dev.sharanggupta.account.client;

import dev.sharanggupta.account.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("card")
public interface CardFeignClient {
    @GetMapping(value = "/api/fetch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDto> fetchCard(@RequestParam String mobileNumber, @RequestHeader("eazybank-correlation-id") String correlationId);
}
