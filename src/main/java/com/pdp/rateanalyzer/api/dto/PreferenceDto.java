package com.pdp.rateanalyzer.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceDto {

  @Schema(name = "Unique identifier of the rate")
  private UUID id;

  @Schema(name = "Unique identifier of the user")
  private UUID userId;

  @Schema(name = "Currency name")
  private String currency;

  @Schema(name = "Expected rate")
  private BigDecimal rate;

}
