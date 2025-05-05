package com.pdp.rateanalyzer.adapter;

import com.pdp.rateanalyzer.domain.Version;

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
  Version update(long value);

}
