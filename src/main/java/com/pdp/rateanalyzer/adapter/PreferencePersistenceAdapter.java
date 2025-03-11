package com.pdp.rateanalyzer.adapter;

import com.pdp.rateanalyzer.domain.PreferenceEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface PreferencePersistenceAdapter {

  /**
   * Streams all preferences.
   *
   * @return Stream of preferences
   */
  Stream<PreferenceEntity> getAll();

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
