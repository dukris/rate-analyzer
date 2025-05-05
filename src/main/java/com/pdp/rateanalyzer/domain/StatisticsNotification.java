package com.pdp.rateanalyzer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsNotification {

    private UUID userId;
    private String currency;
    private BigDecimal highest;
    private BigDecimal lowest;
    private BigDecimal average;
    private BigDecimal percent;
    private LocalDateTime date;

}
