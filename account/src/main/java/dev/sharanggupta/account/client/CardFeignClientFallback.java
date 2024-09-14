package dev.sharanggupta.account.client;

import dev.sharanggupta.account.dto.CardDto;
import org.springframework.http.ResponseEntity;

public class CardFeignClientFallback implements CardFeignClient{
    @Override
    public ResponseEntity<CardDto> fetchCard(String mobileNumber, String correlationId) {
        return null;
    }
}
