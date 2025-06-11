package com.pdp.rateanalyzer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Preference {

  private UUID id;
  private UUID userId;
  private String currency;
  private BigDecimal rate;
  private LocalDateTime date;

}
