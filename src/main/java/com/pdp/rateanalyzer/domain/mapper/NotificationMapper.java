package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.domain.Notification;
import com.pdp.rateanalyzer.messaging.command.SendNotificationPayload;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

  SendNotificationPayload toPayload(Notification notification);

}
