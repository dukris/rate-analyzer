package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.domain.mapper.RateMapper;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.usecase.AnalyzeRatesUseCase;
import com.pdp.rateanalyzer.usecase.ScheduleAnalysisUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticalScheduler implements ScheduleAnalysisUseCase {

  private final CurrencyGateway currencyGateway;
  private final AnalyzeRatesUseCase analyzer;
  private final RateMapper rateMapper;

  @Override
  @Scheduled(cron = "* * * * * *")
  public void schedule() {
    PollingResponseDto rates = currencyGateway.poll(0L, 1L);
    analyzer.analyze(rateMapper.toDomain(rates.getRates()));
  }

}
