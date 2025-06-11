package com.pdp.rateanalyzer.gateway.client;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import com.pdp.rateanalyzer.api.dto.RateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "currency")
public interface CurrencyFetcherClient {

    /**
     * Get updated currencies and rates.
     *
     * @return List of currencies and rates
     */
    @GetMapping("/api/v1/rates/poll")
    ResponseEntity<PollingResponseDto> poll(@RequestParam Long version, @RequestParam Long timeout);

    /**
     * Get currencies and rates by period.
     *
     * @return List of currencies and rates
     */
    @GetMapping("/api/v1/rates/currencies")
    ResponseEntity<List<RateDto>> retrieveByPeriod(
        @RequestParam String currency,
        @RequestParam LocalDateTime from,
        @RequestParam LocalDateTime to
    );

}
