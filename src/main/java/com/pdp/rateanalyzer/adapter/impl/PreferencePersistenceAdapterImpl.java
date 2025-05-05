package com.pdp.rateanalyzer.adapter.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.adapter.repository.PreferenceRepository;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.mapper.PreferenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PreferencePersistenceAdapterImpl implements PreferencePersistenceAdapter {

    public final PreferenceRepository repository;
    private final PreferenceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Stream<Preference> getAll() {
        return mapper.toModel(repository.streamAllBy());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Preference> getAllByUser(UUID user) {
        return mapper.toModel(repository.findAllByUserId(user));
    }

    @Override
    @Transactional
    public Preference save(Preference preference) {
        return mapper.toModel(
            repository.save(mapper.toEntity(preference))
        );
    }

}
