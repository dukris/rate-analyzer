package com.pdp.rateanalyzer.usecase.impl;

import static java.util.Objects.isNull;

import com.pdp.rateanalyzer.adapter.VersionPersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.domain.mapper.RateMapper;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.usecase.AnalyzeRatesUseCase;
import com.pdp.rateanalyzer.usecase.ScheduleAnalysisUseCase;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticalScheduler implements ScheduleAnalysisUseCase {

    private final CurrencyGateway currencyGateway;
    private final VersionPersistenceAdapter versionPersistenceAdapter;
    private final AnalyzeRatesUseCase analyzer;
    private final RateMapper rateMapper;

    @Async
    @Override
    @Scheduled(cron = "* * * * * *")
    @SchedulerLock(name = "calculateAnalyticsLock")
    public void schedule() {
        PollingResponseDto response = currencyGateway.poll(versionPersistenceAdapter.current(), 1L);
        if (!isNull(response.getVersion())) {
            versionPersistenceAdapter.update(response.getVersion());
        }
        analyzer.analyze(rateMapper.toModel(response.getRates()));
    }

}
