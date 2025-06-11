package com.pdp.rateanalyzer.usecase.impl;

import static java.util.Objects.isNull;

import com.pdp.rateanalyzer.domain.MetricsData;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import com.pdp.rateanalyzer.usecase.CalculateMetricsUseCase;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricsCalculator implements CalculateMetricsUseCase {

  @Override
  public WeeklyStatistics calculate(MetricsData data) {
    List<Rate> rates = isNull(data.getRates()) ? new ArrayList<>() : data.getRates();
    Preference preference = data.getPreference();
    BigDecimal highest = rates.stream()
        .map(Rate::getValue)
        .max(BigDecimal::compareTo)
        .orElse(BigDecimal.ZERO);
    BigDecimal lowest = rates.stream()
        .map(Rate::getValue)
        .min(BigDecimal::compareTo)
        .orElse(BigDecimal.ZERO);
    BigDecimal average = rates.stream()
        .map(Rate::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .divide(new BigDecimal(rates.isEmpty() ? 1 : rates.size()), RoundingMode.HALF_UP);
    BigDecimal firstRate = rates.isEmpty() ? BigDecimal.ZERO : rates.get(0).getValue();
    BigDecimal lastRate = rates.isEmpty() ? BigDecimal.ZERO : rates.get(rates.size() - 1).getValue();
    BigDecimal percent = lastRate.subtract(firstRate)
        .divide(firstRate.equals(BigDecimal.ZERO) ? BigDecimal.ONE : firstRate, 4, RoundingMode.HALF_UP)
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
