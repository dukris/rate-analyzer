package com.pdp.rateanalyzer.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.domain.Notification;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.extensions.FakePreferenceEntity;
import com.pdp.rateanalyzer.extensions.FakeRate;
import com.pdp.rateanalyzer.usecase.SendRateNotificationUseCase;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RateAnalyzerTest {

  @Mock
  private ExecutorService executor;

  @Mock
  private SendRateNotificationUseCase sender;

  @Mock
  private PreferencePersistenceAdapter preferencePersistenceAdapter;

  @InjectMocks
  private RatesAnalyzer ratesAnalyzer;

  @Captor
  private ArgumentCaptor<Notification> captor;

  @BeforeEach
  void setUp() {
    doAnswer(invocation -> {
      Runnable task = invocation.getArgument(0);
      task.run();
      return null;
    }).when(executor).execute(any(Runnable.class));
  }

  @Test
  @ExtendWith({FakePreferenceEntity.class, FakeRate.class})
  void shouldSendNotificationWhenRateMatchesPreference(PreferenceEntity preference, Rate rate) {
    // given
    when(preferencePersistenceAdapter.getAll()).thenReturn(Stream.<PreferenceEntity>builder().add(preference).build());

    // when
    ratesAnalyzer.analyze(List.of(rate));

    // then
    verify(sender, timeout(1000)).send(captor.capture());
    Notification notification = captor.getValue();
    assertNotNull(notification);
    assertEquals(preference.getCurrency(), notification.getCurrency());
    assertEquals(preference.getRate(), notification.getRate());
  }

  @Test
  @ExtendWith({FakePreferenceEntity.class, FakeRate.class})
  void shouldNotSendNotificationWhenNoRateMatchesPreference(PreferenceEntity preference, Rate rate) {
    // given
    when(preferencePersistenceAdapter.getAll()).thenReturn(Stream.<PreferenceEntity>builder().add(preference).build());
    rate.setValue(BigDecimal.ZERO);

    // when
    ratesAnalyzer.analyze(List.of(rate));

    // then
    verifyNoInteractions(sender);
  }

}
