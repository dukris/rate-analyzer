package com.pdp.rateanalyzer.messaging.command;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationPayload {

  private UUID userId;
  private String currency;
  private BigDecimal rate;

}
