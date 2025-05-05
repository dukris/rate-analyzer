package com.pdp.rateanalyzer.messaging.command;

import com.pdp.rateanalyzer.messaging.payload.SendRateNotificationPayload;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SendRateNotificationCommand extends RateCommand<SendRateNotificationPayload> {

    public SendRateNotificationCommand(SendRateNotificationPayload payload) {
        super(payload);
    }

}
