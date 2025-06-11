package com.pdp.rateanalyzer.adapter.impl;

import com.pdp.rateanalyzer.adapter.VersionPersistenceAdapter;
import com.pdp.rateanalyzer.adapter.repository.VersionRepository;
import com.pdp.rateanalyzer.domain.Version;
import com.pdp.rateanalyzer.domain.VersionEntity;
import com.pdp.rateanalyzer.domain.mapper.VersionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VersionPersistenceAdapterImpl implements VersionPersistenceAdapter {

    public final VersionRepository repository;
    private final VersionMapper mapper;

    @Override
    @Transactional
    public long current() {
        return repository.findById(0L)
            .orElseGet(() -> repository.save(new VersionEntity(0L, 0L)))
            .getValue();
    }

    @Override
    @Transactional
    public Version update(long value) {
        return mapper.toModel(repository.save(new VersionEntity(0L, value)));
    }

}
