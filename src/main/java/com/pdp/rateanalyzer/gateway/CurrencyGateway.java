package com.pdp.rateanalyzer.gateway;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.api.dto.RateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyGateway {

    /**
     * Poll currencies and rates via long polling.
     *
     * @return List of currencies and rates
     */
    PollingResponseDto poll(Long version, Long timeout);

    /**
     * Retrieve currencies and rates by period of time.
     *
     * @return List of currencies and rates
     */
    List<RateDto> retrieveByPeriod(String currency, LocalDateTime from, LocalDateTime to);

}
