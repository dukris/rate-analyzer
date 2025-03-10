package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticalScheduler implements ScheduleAnalysisUseCase {

  private final CurrencyGateway currencyGateway;

  @Override
  @Scheduled(cron = "* * * * * *")
  public void schedule() {
    PollingResponseDto rates = currencyGateway.poll(0L, 1L);
    // todo compare rates using the corresponding use case

  }

}
