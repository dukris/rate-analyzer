package com.pdp.rateanalyzer.adapter;

import com.pdp.rateanalyzer.adapter.repository.PreferenceRepository;
import com.pdp.rateanalyzer.domain.Preference;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PreferencePersistenceAdapterImpl implements PreferencePersistenceAdapter {

  public final PreferenceRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<Preference> getAllByUser(UUID user) {
    return repository.findAllByUserId(user);
  }

  @Override
  @Transactional
  public Preference save(Preference preference) {
    return repository.save(preference);
  }

}
