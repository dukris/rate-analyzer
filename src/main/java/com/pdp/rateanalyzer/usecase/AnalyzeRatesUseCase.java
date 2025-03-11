package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.Rate;
import java.util.List;

public interface AnalyzeRatesUseCase {

  /**
   * Analyze received rates and compare them with preferences.
   *
   * @param rates Fetched rates
   */
  void analyze(List<Rate> rates);

}
