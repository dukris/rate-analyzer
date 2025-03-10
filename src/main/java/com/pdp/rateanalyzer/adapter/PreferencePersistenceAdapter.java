package com.pdp.rateanalyzer.adapter;

import com.pdp.rateanalyzer.domain.PreferenceEntity;
import java.util.List;
import java.util.UUID;

public interface PreferencePersistenceAdapter {

  /**
   * Retrieves preferences for user.
   *
   * @param user User id
   * @return List of preferences
   */
  List<PreferenceEntity> getAllByUser(UUID user);

  /**
   * Saves preference
   *
   * @param preference User's preference
   * @return Saved preference
   */
  PreferenceEntity save(PreferenceEntity preference);

}
