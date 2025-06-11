package com.pdp.rateanalyzer.adapter.impl;

import com.pdp.rateanalyzer.adapter.repository.PreferenceRepository;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import com.pdp.rateanalyzer.domain.mapper.PreferenceMapper;
import com.pdp.rateanalyzer.extensions.FakePreference;
import com.pdp.rateanalyzer.extensions.FakePreferenceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PreferencePersistenceAdapterImplTest {

  @Mock
  private PreferenceRepository repository;
  @Mock
  private PreferenceMapper mapper;
  @InjectMocks
  private PreferencePersistenceAdapterImpl adapter;

  @Test
  @ExtendWith({FakePreferenceEntity.class, FakePreference.class})
  void shouldSaveNewPreferences(PreferenceEntity entity, Preference model) {
    // given
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toModel(entity)).thenReturn(model);
    when(mapper.toEntity(model)).thenReturn(entity);

    // when
    Preference actual = adapter.save(model);

    // then
    assertNotNull(actual);
    assertEquals(model, actual);
    verify(repository).save(entity);
  }

  @Test
  @ExtendWith({FakePreferenceEntity.class, FakePreference.class})
  void shouldGetAllPreferencesByUser(PreferenceEntity entity, Preference model) {
    // given
    UUID user = UUID.randomUUID();
    when(repository.findAllByUserId(user)).thenReturn(List.of(entity));
    when(mapper.toModel(List.of(entity))).thenReturn(List.of(model));

    // when
    List<Preference> actual = adapter.getAllByUser(user);

    // then
    assertNotNull(actual);
    assertEquals(1, actual.size());
    assertEquals(model, actual.get(0));
    verify(repository).findAllByUserId(user);
  }

  @Test
  @ExtendWith({FakePreferenceEntity.class, FakePreference.class})
  void shouldStreamAllPreferences(PreferenceEntity entity, Preference model) {
    // given
    when(repository.streamAllBy()).thenReturn(Stream.<PreferenceEntity>builder().add(entity).build());
    when(mapper.toModel(any(Stream.class))).thenReturn(Stream.of(model));

    // when
    List<Preference> actual = adapter.getAll().toList();

    // then
    assertNotNull(actual);
    assertEquals(1, actual.size());
    assertEquals(model, actual.get(0));
    verify(repository).streamAllBy();
  }

}
