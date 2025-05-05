package com.pdp.rateanalyzer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

  private String currency;
  private BigDecimal highest;
  private BigDecimal lowest;
  private BigDecimal average;
  private BigDecimal percent;
  private LocalDateTime date;

}
