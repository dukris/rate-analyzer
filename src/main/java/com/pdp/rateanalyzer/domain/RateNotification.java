package com.pdp.rateanalyzer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateNotification {

    private UUID userId;
    private String currency;
    private BigDecimal rate;

}
