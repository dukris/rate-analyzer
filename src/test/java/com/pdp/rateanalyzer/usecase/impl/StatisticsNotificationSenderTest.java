package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.domain.StatisticsNotification;
import com.pdp.rateanalyzer.domain.mapper.NotificationMapper;
import com.pdp.rateanalyzer.extensions.FakeSendStatisticsNotificationPayload;
import com.pdp.rateanalyzer.extensions.FakeStatisticsNotification;
import com.pdp.rateanalyzer.messaging.MessageSender;
import com.pdp.rateanalyzer.messaging.command.Message;
import com.pdp.rateanalyzer.messaging.command.SendStatisticsNotificationCommand;
import com.pdp.rateanalyzer.messaging.payload.SendStatisticsNotificationPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsNotificationSenderTest {

    @Mock
    private MessageSender sender;
    @Mock
    private NotificationMapper mapper;
    @InjectMocks
    private StatisticsNotificationSender statisticsNotificationSender;

    @Captor
    private ArgumentCaptor<Message> captor;

    @Test
    @ExtendWith({FakeStatisticsNotification.class, FakeSendStatisticsNotificationPayload.class})
    void shouldSendMessage(StatisticsNotification data, SendStatisticsNotificationPayload payload) {
        // given
        when(mapper.toPayload(data)).thenReturn(payload);

        // when
        statisticsNotificationSender.send(data);

        // then
        verify(sender).send(captor.capture());
        SendStatisticsNotificationCommand command = (SendStatisticsNotificationCommand) captor.getValue();
        assertNotNull(command);
        assertEquals(payload, command.getPayload());
    }

}
