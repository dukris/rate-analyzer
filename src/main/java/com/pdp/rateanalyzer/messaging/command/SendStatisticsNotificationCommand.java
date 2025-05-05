package com.pdp.rateanalyzer.messaging.command;

import com.pdp.rateanalyzer.messaging.payload.SendStatisticsNotificationPayload;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SendStatisticsNotificationCommand extends RateCommand<SendStatisticsNotificationPayload> {

    public SendStatisticsNotificationCommand(SendStatisticsNotificationPayload payload) {
        super(payload);
    }

}
