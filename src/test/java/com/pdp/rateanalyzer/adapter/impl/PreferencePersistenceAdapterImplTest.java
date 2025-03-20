package com.pdp.rateanalyzer.adapter.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pdp.rateanalyzer.adapter.repository.PreferenceRepository;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import com.pdp.rateanalyzer.extensions.FakePreferenceEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PreferencePersistenceAdapterImplTest {

  @Mock
  private PreferenceRepository repository;
  @InjectMocks
  private PreferencePersistenceAdapterImpl adapter;

  @Test
  @ExtendWith(FakePreferenceEntity.class)
  void shouldSaveNewPreferences(PreferenceEntity expected) {
    // given
    when(repository.save(expected)).thenReturn(expected);

    // when
    PreferenceEntity actual = adapter.save(expected);

    // then
    assertNotNull(actual);
    assertEquals(expected, actual);
    verify(repository).save(any());
  }

  @Test
  @ExtendWith(FakePreferenceEntity.class)
  void shouldGetAllPreferencesByUser(PreferenceEntity expected) {
    // given
    UUID user = UUID.randomUUID();
    when(repository.findAllByUserId(user)).thenReturn(List.of(expected));

    // when
    List<PreferenceEntity> actual = adapter.getAllByUser(user);

    // then
    assertNotNull(actual);
    assertEquals(1, actual.size());
    assertEquals(expected, actual.get(0));
    verify(repository).findAllByUserId(user);
  }

  @Test
  @ExtendWith(FakePreferenceEntity.class)
  void shouldStreamAllPreferences(PreferenceEntity expected) {
    // given
    when(repository.streamAllBy()).thenReturn(Stream.<PreferenceEntity>builder().add(expected).build());

    // when
    List<PreferenceEntity> actual = adapter.getAll().toList();

    // then
    assertNotNull(actual);
    assertEquals(1, actual.size());
    assertEquals(expected, actual.get(0));
    verify(repository).streamAllBy();
  }

}
