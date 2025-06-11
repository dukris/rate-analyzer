package com.pdp.rateanalyzer.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.StatisticsNotification;
import com.pdp.rateanalyzer.domain.WeeklyStatistics;
import com.pdp.rateanalyzer.extensions.FakePreference;
import com.pdp.rateanalyzer.extensions.FakeWeeklyStatistics;
import com.pdp.rateanalyzer.usecase.PrepareStatisticsUseCase;
import com.pdp.rateanalyzer.usecase.SendStatisticsNotificationUseCase;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeeklyStatisticsSchedulerTest {

  @Mock
  private PrepareStatisticsUseCase preparer;
  @Mock
  private SendStatisticsNotificationUseCase sender;
  @Mock
  private PreferencePersistenceAdapter preferencePersistenceAdapter;
  @InjectMocks
  private WeeklyStatisticsScheduler scheduler;

  @Captor
  private ArgumentCaptor<StatisticsNotification> notificationCaptor;

  @Test
  @ExtendWith({FakePreference.class, FakeWeeklyStatistics.class})
  void shouldProcessRatesIfVersionIsNull(Preference preference, WeeklyStatistics statistics) {
    // given
    when(preferencePersistenceAdapter.getAll()).thenReturn(Stream.of(preference));
    when(preparer.prepare(preference.getUserId())).thenReturn(List.of(statistics));

    // when
    scheduler.schedule();

    // then
    verify(preparer).prepare(preference.getUserId());
    verify(sender).send(notificationCaptor.capture());

    StatisticsNotification notification = notificationCaptor.getValue();
    assertNotNull(notification);
    assertEquals(preference.getUserId(), notification.getUserId());
    assertEquals(statistics.getLowest(), notification.getLowest());
    assertEquals(statistics.getHighest(), notification.getHighest());
    assertEquals(statistics.getAverage(), notification.getAverage());
    assertEquals(statistics.getPercent(), notification.getPercent());
  }

}
