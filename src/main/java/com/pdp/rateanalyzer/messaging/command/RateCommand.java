package com.pdp.rateanalyzer.messaging.command;

public abstract class RateCommand<P> extends Message<P> {

  protected RateCommand() {
  }

  protected RateCommand(P payload) {
    super(payload);
  }

  @Override
  public String getTopic() {
    return "rate_command";
  }

}
