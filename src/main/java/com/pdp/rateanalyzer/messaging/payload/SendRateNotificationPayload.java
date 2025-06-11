package com.pdp.rateanalyzer.messaging.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendRateNotificationPayload {

    private UUID userId;
    private String currency;
    private BigDecimal rate;

}
