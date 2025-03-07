package com.pdp.rateanalyzer.messaging;

import com.pdp.rateanalyzer.messaging.command.Message;

public interface MessageSender {

  /**
   * Sends provided message.
   *
   * @param message Command
   */
  void send(Message message);

}
