package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.RateNotification;

public interface SendRateNotificationUseCase {

  /**
   * Sends notification to user about rates.
   *
   * @param notification Notification data
   */
  void send(RateNotification notification);

}
