package com.pdp.rateanalyzer.gateway.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.extensions.FakePollingResponseDto;
import com.pdp.rateanalyzer.gateway.client.CurrencyFetcherClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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

}
