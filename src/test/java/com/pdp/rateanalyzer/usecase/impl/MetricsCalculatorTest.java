package com.pdp.rateanalyzer.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pdp.rateanalyzer.domain.MetricsData;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import com.pdp.rateanalyzer.extensions.FakePreference;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MetricsCalculatorTest {

  @InjectMocks
  private MetricsCalculator calculator;

  @Test
  @ExtendWith(FakePreference.class)
  void shouldCalculateCorrectWeeklyStatistics(Preference preference) {
    // given
    String currency = preference.getCurrency();

    List<Rate> rates = List.of(
        new Rate(UUID.randomUUID(), currency, new BigDecimal("100.00")),
        new Rate(UUID.randomUUID(), currency, new BigDecimal("105.00")),
        new Rate(UUID.randomUUID(), currency, new BigDecimal("95.00")),
        new Rate(UUID.randomUUID(), currency, new BigDecimal("110.00"))
    );

    MetricsData data = new MetricsData(preference, rates);

    // when
    WeeklyStatistics result = calculator.calculate(data);

    // then
    assertNotNull(result);
    assertEquals(preference.getUserId(), result.getUserId());
    assertEquals(currency, result.getCurrency());
    assertEquals(new BigDecimal("110.00"), result.getHighest());
    assertEquals(new BigDecimal("95.00"), result.getLowest());
    assertEquals(new BigDecimal("102.50"), result.getAverage());
    assertEquals(new BigDecimal("10.0000"), result.getPercent());
  }

  @Test
  @ExtendWith(FakePreference.class)
  void shouldHandleEmptyRates(Preference preference) {
    // given
    MetricsData data = new MetricsData(preference, List.of());

    // when
    WeeklyStatistics result = calculator.calculate(data);

    // then
    assertNotNull(result);
    assertEquals(preference.getUserId(), result.getUserId());
    assertEquals(BigDecimal.ZERO, result.getHighest());
    assertEquals(BigDecimal.ZERO, result.getLowest());
    assertEquals(BigDecimal.ZERO, result.getAverage());
    assertEquals(new BigDecimal("0.0000"), result.getPercent());
  }

  @Test
  @ExtendWith(FakePreference.class)
  void shouldHandleSingleRate(Preference preference) {
    // given
    Rate rate = new Rate(UUID.randomUUID(), preference.getCurrency(), new BigDecimal("100.00"));
    MetricsData data = new MetricsData(preference, List.of(rate));

    // when
    WeeklyStatistics result = calculator.calculate(data);

    // then
    assertNotNull(result);
    assertEquals(preference.getUserId(), result.getUserId());
    assertEquals(new BigDecimal("100.00"), result.getHighest());
    assertEquals(new BigDecimal("100.00"), result.getLowest());
    assertEquals(new BigDecimal("100.00"), result.getAverage());
    assertEquals(new BigDecimal("0.0000"), result.getPercent());
  }

}
