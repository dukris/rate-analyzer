package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.domain.Version;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class FakeVersion implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return Version.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return new Version(0L, 0L);
  }

}