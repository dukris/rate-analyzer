package com.pdp.rateanalyzer.adapter.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import integration.IntegrationTest;
import integration.PostgresIntegration;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

class PreferencePersistenceAdapterIT extends IntegrationTest implements PostgresIntegration {

  @Autowired
  private PreferencePersistenceAdapter adapter;

  @Test
  void shouldSavePreference() {
    // given
    PreferenceEntity expected = new PreferenceEntity();
    expected.setUserId(UUID.randomUUID());
    expected.setCurrency("USDT");
    expected.setRate(new BigDecimal("1.0000000000"));

    // when
    PreferenceEntity actual = adapter.save(expected);

    // then
    assertNotNull(actual);
    assertEquals(expected.getCurrency(), actual.getCurrency());
    assertEquals(expected.getRate(), actual.getRate());
    assertEquals(expected.getUserId(), actual.getUserId());
  }

  @Test
  @Sql(scripts = {"classpath:sql/insert-preference.sql"})
  void shouldReturnPreferencesByUser() {
    // given
    UUID user = UUID.fromString("967a7ce6-2b47-4a9f-bde1-78401509e82d");
    PreferenceEntity expected = new PreferenceEntity();
    expected.setUserId(user);
    expected.setCurrency("USDT");
    expected.setRate(new BigDecimal("1.0000000000"));

    // when
    List<PreferenceEntity> actual = adapter.getAllByUser(user);

    // then
    assertNotNull(actual);
    assertEquals(1, actual.size());
    assertEquals(expected.getCurrency(), actual.get(0).getCurrency());
    assertEquals(expected.getRate(), actual.get(0).getRate());
    assertEquals(expected.getUserId(), actual.get(0).getUserId());
  }

}
