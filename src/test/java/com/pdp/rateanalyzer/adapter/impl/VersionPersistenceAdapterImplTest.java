package com.pdp.rateanalyzer.adapter.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.adapter.repository.VersionRepository;
import com.pdp.rateanalyzer.domain.VersionEntity;
import com.pdp.rateanalyzer.extensions.FakeVersionEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VersionPersistenceAdapterImplTest {

  @Mock
  private VersionRepository repository;
  @InjectMocks
  private VersionPersistenceAdapterImpl adapter;

  @Test
  @ExtendWith(FakeVersionEntity.class)
  void shouldUpdateVersion(VersionEntity expected) {
    // given
    when(repository.save(expected)).thenReturn(expected);

    // when
    VersionEntity actual = adapter.update(expected.getValue());

    // then
    assertNotNull(actual);
    assertEquals(expected, actual);
    verify(repository).save(expected);
  }

  @Test
  @ExtendWith(FakeVersionEntity.class)
  void shouldReturnCurrentVersion(VersionEntity expected) {
    // given
    when(repository.findById(0L)).thenReturn(Optional.of(expected));

    // when
    long actual = adapter.current();

    // then
    assertEquals(expected.getValue(), actual);
    verify(repository).findById(0L);
  }

  @Test
  @ExtendWith(FakeVersionEntity.class)
  void shouldSaveVersionIfCurrentVersionIsEmpty(VersionEntity expected) {
    // given
    when(repository.findById(0L)).thenReturn(Optional.empty());
    when(repository.save(any())).thenReturn(expected);

    // when
    long actual = adapter.current();

    // then
    assertEquals(expected.getValue(), actual);
    verify(repository).findById(0L);
    verify(repository).save(any());
  }

}
