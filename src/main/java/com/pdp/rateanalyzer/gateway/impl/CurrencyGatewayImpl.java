package com.pdp.rateanalyzer.gateway.impl;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.gateway.client.CurrencyFetcherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyGatewayImpl implements CurrencyGateway {

    private final CurrencyFetcherClient client;

    @Override
    public PollingResponseDto poll(Long version, Long timeout) {
        ResponseEntity<PollingResponseDto> response = client.poll(version, timeout);
        return HttpStatus.OK.equals(response.getStatusCode())
            ? response.getBody()
            : new PollingResponseDto();
    }

    @Override
    public List<RateDto> retrieveByPeriod(String currency, LocalDateTime from, LocalDateTime to) {
        return client.retrieveByPeriod(currency, from, to).getBody();
    }

}
