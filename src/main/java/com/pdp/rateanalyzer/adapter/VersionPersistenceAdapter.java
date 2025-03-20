package com.pdp.rateanalyzer.adapter;

import com.pdp.rateanalyzer.domain.VersionEntity;

public interface VersionPersistenceAdapter {

  /**
   * Retrieves current version.
   *
   * @return Current version value
   */
  long current();

  /**
   * Updates version.
   *
   * @param value Version value
   * @return Saved version value
   */
  VersionEntity update(long value);

}
