package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.domain.MetricsData;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import com.pdp.rateanalyzer.domain.mapper.RateMapper;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.usecase.CalculateMetricsUseCase;
import com.pdp.rateanalyzer.usecase.PrepareStatisticsUseCase;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticsPreparer implements PrepareStatisticsUseCase { // todo unit tests

  private final CurrencyGateway currencyGateway;
  private final PreferencePersistenceAdapter preferencePersistenceAdapter;
  private final CalculateMetricsUseCase calculator;
  private final RateMapper rateMapper;

  @Override
  public List<WeeklyStatistics> prepare(UUID userId) { // todo improve performance
    List<WeeklyStatistics> statistics = new ArrayList<>();
    List<Preference> preferences = preferencePersistenceAdapter.getAllByUser(userId);
    preferences.forEach(preference -> {
      List<RateDto> rates = currencyGateway.retrieveByPeriod(
          preference.getCurrency(),
          LocalDateTime.now().minusDays(7),
          LocalDateTime.now()
      );
      statistics.add(calculator.calculate(new MetricsData(preference, rateMapper.toModel(rates))));
    });
    return statistics;
  }

}
