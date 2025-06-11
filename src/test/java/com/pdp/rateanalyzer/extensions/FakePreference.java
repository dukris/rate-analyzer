package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.domain.Preference;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FakePreference implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return Preference.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return new Preference(UUID.randomUUID(), UUID.randomUUID(), "USDT", new BigDecimal(1), LocalDateTime.now());
  }

}