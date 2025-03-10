package com.pdp.rateanalyzer.domain;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

  private UUID id;
  private String currency;
  private BigDecimal value;
}
