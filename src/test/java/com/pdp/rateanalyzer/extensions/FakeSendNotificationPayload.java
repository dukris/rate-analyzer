package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.messaging.command.SendNotificationPayload;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class FakeSendNotificationPayload implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return SendNotificationPayload.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return new SendNotificationPayload(UUID.randomUUID(), "USDT", new BigDecimal(1));
  }

}