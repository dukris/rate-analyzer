package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.domain.MetricsData;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import com.pdp.rateanalyzer.domain.mapper.RateMapper;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.usecase.CalculateMetricsUseCase;
import com.pdp.rateanalyzer.usecase.PrepareStatisticsUseCase;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticsPreparer implements PrepareStatisticsUseCase {

  private final CurrencyGateway currencyGateway;
  private final PreferencePersistenceAdapter preferencePersistenceAdapter;
  private final CalculateMetricsUseCase calculator;
  private final RateMapper rateMapper;

  @Override
  public List<WeeklyStatistics> prepare(UUID userId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime weekAgo = now.minusDays(7);
    return preferencePersistenceAdapter.getAllByUser(userId).parallelStream()
        .map(preference -> {
          List<RateDto> rates = currencyGateway.retrieveByPeriod(preference.getCurrency(), weekAgo, now);
          return calculator.calculate(new MetricsData(preference, rateMapper.toModel(rates)));
        }).toList();
  }

}
