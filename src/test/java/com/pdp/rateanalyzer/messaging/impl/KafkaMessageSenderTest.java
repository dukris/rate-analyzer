package com.pdp.rateanalyzer.messaging.impl;

import com.pdp.rateanalyzer.extensions.FakeSendRateNotificationPayload;
import com.pdp.rateanalyzer.messaging.command.SendRateNotificationCommand;
import com.pdp.rateanalyzer.messaging.payload.SendRateNotificationPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaMessageSenderTest {

    @Mock
    private KafkaTemplate<String, Object> template;
    @InjectMocks
    private KafkaMessageSender sender;

    @Test
    @ExtendWith(FakeSendRateNotificationPayload.class)
    void shouldSendMessage(SendRateNotificationPayload payload) {
        // given
        SendRateNotificationCommand command = new SendRateNotificationCommand(payload);

        // when
        sender.send(command);

        // then
        verify(template).send(command.getTopic(), command.getKey(), command.getPayload());
    }

}
