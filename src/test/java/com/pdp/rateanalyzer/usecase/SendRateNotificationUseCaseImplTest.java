package com.pdp.rateanalyzer.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.extensions.FakeNotificationData;
import com.pdp.rateanalyzer.extensions.FakeSendNotificationPayload;
import com.pdp.rateanalyzer.messaging.MessageSender;
import com.pdp.rateanalyzer.messaging.command.Message;
import com.pdp.rateanalyzer.messaging.command.SendNotificationCommand;
import com.pdp.rateanalyzer.messaging.command.SendNotificationPayload;
import com.pdp.rateanalyzer.usecase.SendRateNotificationUseCase.NotificationData;
import com.pdp.rateanalyzer.usecase.SendRateNotificationUseCase.NotificationDataMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SendRateNotificationUseCaseImplTest {

  @Mock
  private MessageSender sender;
  @Mock
  private NotificationDataMapper mapper;
  @InjectMocks
  private SendRateNotificationUseCaseImpl useCase;

  @Captor
  private ArgumentCaptor<Message> captor;

  @Test
  @ExtendWith({FakeNotificationData.class, FakeSendNotificationPayload.class})
  void shouldSendMessage(NotificationData data, SendNotificationPayload payload) {
    // given
    when(mapper.toPayload(data)).thenReturn(payload);

    // when
    useCase.send(data);

    // then
    verify(sender).send(captor.capture());
    SendNotificationCommand command = (SendNotificationCommand) captor.getValue();
    assertNotNull(command);
    assertEquals(payload, command.getPayload());
  }

}
