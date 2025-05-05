package com.pdp.rateanalyzer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

  private UUID id;
  private String currency;
  private BigDecimal value;

}
