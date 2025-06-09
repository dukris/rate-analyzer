package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.StatisticsNotification;
import com.pdp.rateanalyzer.usecase.PrepareStatisticsUseCase;
import com.pdp.rateanalyzer.usecase.ScheduleWeeklyStatisticsUseCase;
import com.pdp.rateanalyzer.usecase.SendStatisticsNotificationUseCase;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeeklyStatisticsScheduler implements ScheduleWeeklyStatisticsUseCase { // todo unit tests

  private final PrepareStatisticsUseCase preparer;
  private final SendStatisticsNotificationUseCase sender;
  private final PreferencePersistenceAdapter preferencePersistenceAdapter;

  @Async
  @Override
  @Scheduled(cron = "0 0 9 ? * SUN")
  @SchedulerLock(name = "calculateWeekReportsLock")
  public void schedule() {
    try (Stream<Preference> preferences = preferencePersistenceAdapter.getAll()) {
      preferences.map(Preference::getUserId)
          .distinct()
          .forEach(userId -> preparer.prepare(userId)
              .forEach(statistics -> sender.send(
                  new StatisticsNotification(
                      userId,
                      statistics.getCurrency(),
                      statistics.getHighest(),
                      statistics.getLowest(),
                      statistics.getAverage(),
                      statistics.getPercent(),
                      LocalDateTime.now()))));
    }
  }

}
