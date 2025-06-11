package com.pdp.rateanalyzer.extensions;

import com.pdp.rateanalyzer.messaging.payload.SendStatisticsNotificationPayload;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FakeSendStatisticsNotificationPayload implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return SendStatisticsNotificationPayload.class.isAssignableFrom(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return new SendStatisticsNotificationPayload(
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