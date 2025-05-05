package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.domain.StatisticsNotification;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FakeStatisticsNotification implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return StatisticsNotification.class.isAssignableFrom(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return new StatisticsNotification(
            UUID.randomUUID(),
            "USDT",
            BigDecimal.ONE,
            BigDecimal.TEN,
            BigDecimal.TWO,
            BigDecimal.ZERO,
            LocalDateTime.now()
        );
    }

}