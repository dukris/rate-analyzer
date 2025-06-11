package com.pdp.rateanalyzer.gateway.impl;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.extensions.FakePollingResponseDto;
import com.pdp.rateanalyzer.extensions.FakeRateDto;
import com.pdp.rateanalyzer.gateway.client.CurrencyFetcherClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyGatewayImplTest {

    @Mock
    private CurrencyFetcherClient client;
    @InjectMocks
    private CurrencyGatewayImpl gateway;

    @Test
    @ExtendWith(FakePollingResponseDto.class)
    void shouldPollAllRates(PollingResponseDto expected) {
        // given
        Long version = 0L;
        Long timeout = 1L;
        when(client.poll(version, timeout)).thenReturn(ResponseEntity.ok(expected));

        // when
        PollingResponseDto actual = gateway.poll(version, timeout);

        // then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @ExtendWith(FakeRateDto.class)
    void shouldRetrieveRatesByPeriod(RateDto expected) {
        // given
        String currency = "USD";
        LocalDateTime from = LocalDateTime.now().minusDays(1);
        LocalDateTime to = LocalDateTime.now();
        when(client.retrieveByPeriod(currency, from, to)).thenReturn(ResponseEntity.ok(List.of(expected)));

        // when
        List<RateDto> actual = gateway.retrieveByPeriod(currency, from, to);

        // then
        assertNotNull(actual);
        assertEquals(expected, actual.get(0));
    }


}
