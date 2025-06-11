package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.StatisticsNotification;

public interface SendStatisticsNotificationUseCase {

  /**
   * Sends notification to user about statistics weekly.
   *
   * @param notification Notification data
   */
  void send(StatisticsNotification notification);

}
