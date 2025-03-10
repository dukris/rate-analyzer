package com.pdp.rateanalyzer.gateway;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;

public interface CurrencyGateway {

  /**
   * Gets currencies and rates via long polling.
   *
   * @return List of currencies and rates
   */
  PollingResponseDto poll(Long version, Long timeout);

}
