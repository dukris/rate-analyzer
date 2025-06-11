package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.messaging.payload.SendRateNotificationPayload;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.util.UUID;

public class FakeSendRateNotificationPayload implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return SendRateNotificationPayload.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return new SendRateNotificationPayload(UUID.randomUUID(), "USDT", new BigDecimal(1));
  }

}