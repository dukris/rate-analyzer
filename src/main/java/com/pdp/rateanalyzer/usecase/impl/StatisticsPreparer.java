package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import com.pdp.rateanalyzer.domain.mapper.RateMapper;
import com.pdp.rateanalyzer.gateway.CurrencyGateway;
import com.pdp.rateanalyzer.usecase.PrepareStatisticsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StatisticsPreparer implements PrepareStatisticsUseCase { // todo unit tests

    private final CurrencyGateway currencyGateway;
    private final PreferencePersistenceAdapter preferencePersistenceAdapter;
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
            statistics.add(calculateMetrics(preference, rateMapper.toModel(rates)));
        });
        return statistics;
    }

    // todo avoid private method
    private WeeklyStatistics calculateMetrics(Preference preference, List<Rate> rates) {
        BigDecimal highest = rates.stream().map(Rate::getValue).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        BigDecimal lowest = rates.stream().map(Rate::getValue).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        BigDecimal average = rates.stream().map(Rate::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(new BigDecimal(rates.size()), RoundingMode.HALF_UP);
        BigDecimal firstRate = rates.get(0).getValue();
        BigDecimal lastRate = rates.get(rates.size() - 1).getValue();
        BigDecimal percent = lastRate.subtract(firstRate)
            .divide(firstRate, 4, RoundingMode.HALF_UP)
            .multiply(new BigDecimal(100));
        return new WeeklyStatistics(
            preference.getUserId(),
            preference.getCurrency(),
            highest,
            lowest,
            average,
            percent,
            LocalDateTime.now()
        );
    }

}
