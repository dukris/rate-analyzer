package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.MetricsData;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;

public interface CalculateMetricsUseCase {

  /**
   * Calculate metrics weekly.
   *
   * @param data Metrics data
   * @return Calculated statistics
   */
  WeeklyStatistics calculate(MetricsData data);

}
