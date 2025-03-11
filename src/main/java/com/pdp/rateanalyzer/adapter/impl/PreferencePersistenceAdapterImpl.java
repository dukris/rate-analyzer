package com.pdp.rateanalyzer.adapter.impl;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.adapter.repository.PreferenceRepository;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PreferencePersistenceAdapterImpl implements PreferencePersistenceAdapter {

  public final PreferenceRepository repository;

  @Override
  @Transactional(readOnly = true)
  public Stream<PreferenceEntity> getAll() {
    return repository.streamAllBy();
  }

  @Override
  @Transactional(readOnly = true)
  public List<PreferenceEntity> getAllByUser(UUID user) {
    return repository.findAllByUserId(user);
  }

  @Override
  @Transactional
  public PreferenceEntity save(PreferenceEntity preference) {
    return repository.save(preference);
  }

}
