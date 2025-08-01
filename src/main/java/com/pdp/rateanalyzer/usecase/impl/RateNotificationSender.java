package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.domain.RateNotification;
import com.pdp.rateanalyzer.domain.mapper.NotificationMapper;
import com.pdp.rateanalyzer.messaging.MessageSender;
import com.pdp.rateanalyzer.messaging.command.SendRateNotificationCommand;
import com.pdp.rateanalyzer.usecase.SendRateNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateNotificationSender implements SendRateNotificationUseCase {

  private final MessageSender sender;
  private final NotificationMapper mapper;

  @Override
  public void send(RateNotification notification) {
    sender.send(new SendRateNotificationCommand(mapper.toPayload(notification)));
  }

}
