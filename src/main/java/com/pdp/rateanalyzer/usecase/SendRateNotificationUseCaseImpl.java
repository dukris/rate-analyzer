package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.Notification;
import com.pdp.rateanalyzer.domain.mapper.NotificationMapper;
import com.pdp.rateanalyzer.messaging.MessageSender;
import com.pdp.rateanalyzer.messaging.command.SendNotificationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendRateNotificationUseCaseImpl implements SendRateNotificationUseCase {

  private final MessageSender sender;
  private final NotificationMapper mapper;

  @Override
  public void send(Notification data) {
    sender.send(new SendNotificationCommand(mapper.toPayload(data)));
  }

}
