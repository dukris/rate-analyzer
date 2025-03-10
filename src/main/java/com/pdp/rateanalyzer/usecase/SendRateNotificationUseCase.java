package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.PreferenceEntity;
import com.pdp.rateanalyzer.messaging.command.SendNotificationPayload;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

public interface SendRateNotificationUseCase {

  /**
   * Sends notification to user about rates.
   *
   * @param data Notification data
   */
  void send(NotificationData data);

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class NotificationData {

    private UUID userId;
    private String currency;
    private BigDecimal rate;
  }

  @Mapper(componentModel = "spring")
  interface NotificationDataMapper {

    NotificationData toData(PreferenceEntity preference);

    SendNotificationPayload toPayload(NotificationData data);
  }

}
