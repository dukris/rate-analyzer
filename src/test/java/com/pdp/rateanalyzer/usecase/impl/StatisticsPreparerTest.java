package com.pdp.rateanalyzer.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.domain.MetricsData;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import com.pdp.rateanalyzer.domain.mapper.RateMapper;
import com.pdp.rateanalyzer.extensions.FakePreference;
import com.pdp.rateanalyzer.extensions.FakeRate;
import com.pdp.rateanalyzer.extensions.FakeRateDto;
import com.pdp.rateanalyzer.extensions.FakeWeeklyStatistics;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.usecase.CalculateMetricsUseCase;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatisticsPreparerTest {

  private final UUID userId = UUID.randomUUID();

  @Mock
  private CurrencyGateway currencyGateway;
  @Mock
  private PreferencePersistenceAdapter preferencePersistenceAdapter;
  @Mock
  private CalculateMetricsUseCase calculator;
  @Mock
  private RateMapper rateMapper;
  @InjectMocks
  private StatisticsPreparer preparer;

  @Test
  @ExtendWith({FakePreference.class, FakeRateDto.class, FakeRate.class, FakeWeeklyStatistics.class})
  void shouldPrepareStatistics(Preference preference, RateDto rateDto, Rate rate, WeeklyStatistics statistics) {
    // given
    when(preferencePersistenceAdapter.getAllByUser(userId)).thenReturn(List.of(preference));
    when(currencyGateway.retrieveByPeriod(eq(preference.getCurrency()), any(LocalDateTime.class), any(LocalDateTime.class)))
        .thenReturn(List.of(rateDto));
    when(rateMapper.toModel(List.of(rateDto))).thenReturn(List.of(rate));
    when(calculator.calculate(any(MetricsData.class))).thenReturn(statistics);

    // when
    List<WeeklyStatistics> result = preparer.prepare(userId);

    // then
    assertNotNull(result);
    assertEquals(1, result.size());
    assertNotNull(result.getFirst());
    assertEquals(statistics, result.getFirst());
  }

}
