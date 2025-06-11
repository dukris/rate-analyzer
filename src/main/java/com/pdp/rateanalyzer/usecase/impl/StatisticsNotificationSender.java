package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.domain.StatisticsNotification;
import com.pdp.rateanalyzer.domain.mapper.NotificationMapper;
import com.pdp.rateanalyzer.messaging.MessageSender;
import com.pdp.rateanalyzer.messaging.command.SendStatisticsNotificationCommand;
import com.pdp.rateanalyzer.usecase.SendStatisticsNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticsNotificationSender implements SendStatisticsNotificationUseCase {

    private final MessageSender sender;
    private final NotificationMapper mapper;

    @Override
    public void send(StatisticsNotification notification) {
        sender.send(new SendStatisticsNotificationCommand(mapper.toPayload(notification)));
    }

}
