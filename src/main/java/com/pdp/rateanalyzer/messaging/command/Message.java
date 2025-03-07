package com.pdp.rateanalyzer.messaging.command;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public abstract class Message<P> {

  private String key;
  private P payload;

  protected Message(P payload) {
    this.key = UUID.randomUUID().toString();
    this.payload = payload;
  }

  public abstract String getTopic();

}
