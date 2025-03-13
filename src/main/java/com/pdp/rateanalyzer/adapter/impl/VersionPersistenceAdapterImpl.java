package com.pdp.rateanalyzer.adapter.impl;

import com.pdp.rateanalyzer.adapter.VersionPersistenceAdapter;
import com.pdp.rateanalyzer.adapter.repository.VersionRepository;
import com.pdp.rateanalyzer.domain.VersionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VersionPersistenceAdapterImpl implements VersionPersistenceAdapter {

  public final VersionRepository repository;

  @Override
  @Transactional
  public long current() {
    return repository.findById(0L)
        .orElseGet(() -> repository.save(new VersionEntity(0L, 0L)))
        .getValue();
  }

  @Override
  @Transactional
  public VersionEntity update(long value) {
    return repository.save(new VersionEntity(0L, value));
  }

}
