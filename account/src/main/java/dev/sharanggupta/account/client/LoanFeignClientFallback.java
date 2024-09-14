package dev.sharanggupta.account.client;

import dev.sharanggupta.account.dto.LoanDto;
import org.springframework.http.ResponseEntity;

public class LoanFeignClientFallback implements LoanFeignClient{
    @Override
    public ResponseEntity<LoanDto> fetchLoan(String mobileNumber, String correlationId) {
        return null;
    }
}
