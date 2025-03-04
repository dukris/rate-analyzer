package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.api.dto.CreatePreferenceDto;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class FakeCreatePreferenceDto implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return CreatePreferenceDto.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return new CreatePreferenceDto(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(1));
  }

}