package com.pdp.rateanalyzer.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollingResponseDto {

  @Schema(name = "Unique identifier of the rate")
  private Long version;

  @Schema(name = "List of currencies and rates")
  List<RateDto> rates;

}
