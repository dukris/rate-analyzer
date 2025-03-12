package com.pdp.rateanalyzer.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePreferenceDto {

  @Schema(name = "Unique identifier of the user")
  @NotNull(message = "User id can't be empty")
  private UUID userId;

  @Schema(name = "Currency name")
  @NotBlank(message = "Currency can't be empty")
  private String currency;

  @Schema(name = "Expected rate")
  @NotNull(message = "Rate can't be empty")
  private BigDecimal rate;

}
