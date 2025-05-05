package com.pdp.rateanalyzer.usecase;

import com.pdp.rateanalyzer.domain.Notification;

public interface SendRateNotificationUseCase {

  /**
   * Sends notification to user about rates.
   *
   * @param notification Notification data
   */
  void send(Notification notification);

}
