package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.domain.RateNotification;
import com.pdp.rateanalyzer.domain.StatisticsNotification;
import com.pdp.rateanalyzer.messaging.payload.SendRateNotificationPayload;
import com.pdp.rateanalyzer.messaging.payload.SendStatisticsNotificationPayload;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    SendRateNotificationPayload toPayload(RateNotification notification);

    SendStatisticsNotificationPayload toPayload(StatisticsNotification notification);

}
