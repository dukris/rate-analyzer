package com.pdp.rateanalyzer.domain;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

  private UUID userId;
  private String currency;
  private BigDecimal rate;

}
