package com.pdp.rateanalyzer.adapter.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pdp.rateanalyzer.adapter.VersionPersistenceAdapter;
import com.pdp.rateanalyzer.domain.VersionEntity;
import integration.IntegrationTest;
import integration.PostgresIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class VersionPersistenceAdapterIT extends IntegrationTest implements PostgresIntegration {

  @Autowired
  private VersionPersistenceAdapter adapter;

  @Test
  void shouldUpdateVersion() {
    // given
    long expected = 2L;

    // when
    VersionEntity actual = adapter.update(expected);

    // then
    assertNotNull(actual);
    assertEquals(0L, actual.getId());
    assertEquals(expected, actual.getValue());
  }

  @Test
  void shouldReturnCurrentVersion() {
    // given
    long expected = 0L;

    // when
    long actual = adapter.current();

    // then
    assertEquals(expected, actual);
  }

}
