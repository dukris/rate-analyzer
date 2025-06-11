package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import java.util.List;
import java.util.UUID;

public interface PrepareStatisticsUseCase {

  /**
   * Prepare statistics based on provided period of time for requested user.
   *
   * @param userId User id
   */
  List<WeeklyStatistics> prepare(UUID userId);

}
