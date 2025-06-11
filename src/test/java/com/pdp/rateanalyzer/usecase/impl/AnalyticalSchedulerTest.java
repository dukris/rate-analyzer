package com.pdp.rateanalyzer.usecase.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.adapter.VersionPersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.domain.mapper.RateMapper;
import com.pdp.rateanalyzer.extensions.FakePollingResponseDto;
import com.pdp.rateanalyzer.extensions.FakeRate;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.usecase.AnalyzeRatesUseCase;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnalyticalSchedulerTest {

  private final Long version = 1L;

  @Mock
  private CurrencyGateway currencyGateway;
  @Mock
  private VersionPersistenceAdapter versionPersistenceAdapter;
  @Mock
  private AnalyzeRatesUseCase analyzer;
  @Mock
  private RateMapper rateMapper;
  @InjectMocks
  private AnalyticalScheduler scheduler;

  @Test
  @ExtendWith({FakePollingResponseDto.class, FakeRate.class})
  void shouldProcessRatesIfVersionIsNull(PollingResponseDto response, Rate rate) {
    // given
    when(versionPersistenceAdapter.current()).thenReturn(version);
    when(currencyGateway.poll(version, 1L)).thenReturn(response);
    when(rateMapper.toModel(response.getRates())).thenReturn(List.of(rate));

    // when
    scheduler.schedule();

    // then
    verify(analyzer).analyze(List.of(rate));
    verify(versionPersistenceAdapter).current();
    verify(versionPersistenceAdapter).update(version);
  }

  @Test
  @ExtendWith({FakePollingResponseDto.class, FakeRate.class})
  void shouldProcessRatesIfVersionIsNotNull(PollingResponseDto response, Rate rate) {
    // given
    response.setVersion(null);
    when(versionPersistenceAdapter.current()).thenReturn(version);
    when(currencyGateway.poll(version, 1L)).thenReturn(response);
    when(rateMapper.toModel(response.getRates())).thenReturn(List.of(rate));

    // when
    scheduler.schedule();

    // then
    verify(analyzer).analyze(List.of(rate));
    verify(versionPersistenceAdapter).current();
  }

}
