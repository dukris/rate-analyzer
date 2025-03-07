package com.pdp.rateanalyzer.messaging.command;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SendNotificationCommand extends RateCommand<SendNotificationPayload> {

  public SendNotificationCommand(SendNotificationPayload payload) {
    super(payload);
  }

}
