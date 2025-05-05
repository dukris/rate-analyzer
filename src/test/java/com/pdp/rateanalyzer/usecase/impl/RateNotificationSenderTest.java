package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.domain.RateNotification;
import com.pdp.rateanalyzer.domain.mapper.NotificationMapper;
import com.pdp.rateanalyzer.extensions.FakeRateNotification;
import com.pdp.rateanalyzer.extensions.FakeSendRateNotificationPayload;
import com.pdp.rateanalyzer.messaging.MessageSender;
import com.pdp.rateanalyzer.messaging.command.Message;
import com.pdp.rateanalyzer.messaging.command.SendRateNotificationCommand;
import com.pdp.rateanalyzer.messaging.payload.SendRateNotificationPayload;
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
class RateNotificationSenderTest {

  @Mock
  private MessageSender sender;
  @Mock
  private NotificationMapper mapper;
  @InjectMocks
  private RateNotificationSender rateNotificationSender;

  @Captor
  private ArgumentCaptor<Message> captor;

  @Test
  @ExtendWith({FakeRateNotification.class, FakeSendRateNotificationPayload.class})
  void shouldSendMessage(RateNotification data, SendRateNotificationPayload payload) {
    // given
    when(mapper.toPayload(data)).thenReturn(payload);

    // when
    rateNotificationSender.send(data);

    // then
    verify(sender).send(captor.capture());
    SendRateNotificationCommand command = (SendRateNotificationCommand) captor.getValue();
    assertNotNull(command);
    assertEquals(payload, command.getPayload());
  }

}
