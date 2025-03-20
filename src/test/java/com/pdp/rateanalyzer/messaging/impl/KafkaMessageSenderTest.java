package com.pdp.rateanalyzer.messaging.impl;

import static org.mockito.Mockito.verify;

import com.pdp.rateanalyzer.extensions.FakeSendNotificationPayload;
import com.pdp.rateanalyzer.messaging.command.SendNotificationCommand;
import com.pdp.rateanalyzer.messaging.command.SendNotificationPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class KafkaMessageSenderTest {

  @Mock
  private KafkaTemplate<String, Object> template;
  @InjectMocks
  private KafkaMessageSender sender;

  @Test
  @ExtendWith(FakeSendNotificationPayload.class)
  void shouldSendMessage(SendNotificationPayload payload) {
    // given
    SendNotificationCommand command = new SendNotificationCommand(payload);

    // when
    sender.send(command);

    // then
    verify(template).send(command.getTopic(), command.getKey(), command.getPayload());
  }

}
