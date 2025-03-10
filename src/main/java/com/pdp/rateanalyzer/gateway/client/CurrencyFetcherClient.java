package com.pdp.rateanalyzer.gateway.client;

import com.pdp.rateanalyzer.api.dto.PollingResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currency")
public interface CurrencyFetcherClient {

  /**
   * Gets currencies and rates.
   *
   * @return List of currencies and rates
   */
  @GetMapping("/api/v1/rates/poll")
  ResponseEntity<PollingResponseDto> poll(@RequestParam Long version, @RequestParam Long timeout);

}
