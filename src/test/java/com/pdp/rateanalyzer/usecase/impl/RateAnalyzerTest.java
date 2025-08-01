package com.pdp.rateanalyzer.usecase.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.domain.RateNotification;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.Rate;
import com.pdp.rateanalyzer.extensions.FakePreference;
import com.pdp.rateanalyzer.extensions.FakeRate;
import com.pdp.rateanalyzer.usecase.SendRateNotificationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
  private ArgumentCaptor<RateNotification> captor;

  @BeforeEach
  void setUp() {
    doAnswer(invocation -> {
      Runnable task = invocation.getArgument(0);
      task.run();
      return null;
    }).when(executor).execute(any(Runnable.class));
  }

  @Test
  @ExtendWith({FakePreference.class, FakeRate.class})
  void shouldSendNotificationWhenRateMatchesPreference(Preference preference, Rate rate) {
    // given
    when(preferencePersistenceAdapter.getAll()).thenReturn(Stream.<Preference>builder().add(preference).build());

    // when
    ratesAnalyzer.analyze(List.of(rate));

    // then
    verify(sender, timeout(1000)).send(captor.capture());
    RateNotification notification = captor.getValue();
    assertNotNull(notification);
    assertEquals(preference.getCurrency(), notification.getCurrency());
    assertEquals(preference.getRate(), notification.getRate());
  }

  @Test
  @ExtendWith({FakePreference.class, FakeRate.class})
  void shouldNotSendNotificationWhenNoRateMatchesPreference(Preference preference, Rate rate) {
    // given
    when(preferencePersistenceAdapter.getAll()).thenReturn(Stream.<Preference>builder().add(preference).build());
    rate.setValue(BigDecimal.ZERO);

    // when
    ratesAnalyzer.analyze(List.of(rate));

    // then
    verifyNoInteractions(sender);
  }

}
