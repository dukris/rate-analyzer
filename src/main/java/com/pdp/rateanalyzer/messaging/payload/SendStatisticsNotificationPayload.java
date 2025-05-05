package com.pdp.rateanalyzer.messaging.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendStatisticsNotificationPayload {

    private UUID userId;
    private String currency;
    private BigDecimal highest;
    private BigDecimal lowest;
    private BigDecimal average;
    private BigDecimal percent;
    private LocalDateTime date;

}
