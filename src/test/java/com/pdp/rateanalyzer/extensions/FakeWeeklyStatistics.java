package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class FakeWeeklyStatistics implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return WeeklyStatistics.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return new WeeklyStatistics(
        UUID.randomUUID(),
        "USDT",
        new BigDecimal(3),
        new BigDecimal(1),
        new BigDecimal(2),
        new BigDecimal(30),
        LocalDateTime.now());
  }

}