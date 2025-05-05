package com.pdp.rateanalyzer.adapter.impl;

import com.pdp.rateanalyzer.adapter.repository.VersionRepository;
import com.pdp.rateanalyzer.domain.Version;
import com.pdp.rateanalyzer.domain.VersionEntity;
import com.pdp.rateanalyzer.domain.mapper.VersionMapper;
import com.pdp.rateanalyzer.extensions.FakeVersion;
import com.pdp.rateanalyzer.extensions.FakeVersionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VersionPersistenceAdapterImplTest {

    @Mock
    private VersionRepository repository;
    @Mock
    private VersionMapper mapper;
    @InjectMocks
    private VersionPersistenceAdapterImpl adapter;

    @Test
    @ExtendWith({FakeVersionEntity.class, FakeVersion.class})
    void shouldUpdateVersion(VersionEntity entity, Version model) {
        // given
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(model);

        // when
        Version actual = adapter.update(entity.getValue());

        // then
        assertNotNull(actual);
        assertEquals(model, actual);
        verify(repository).save(entity);
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
