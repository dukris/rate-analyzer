package com.pdp.rateanalyzer.messaging;

import com.pdp.rateanalyzer.messaging.command.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageSender implements MessageSender {

  private final KafkaTemplate<String, Object> template;

  @Override
  public void send(Message message) {
    template.send(message.getTopic(), message.getKey(), message.getPayload());
  }

}
