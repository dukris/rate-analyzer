package com.pdp.rateanalyzer.adapter.repository;

import com.pdp.rateanalyzer.domain.Preference;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, UUID> {

  List<Preference> findAllByUserId(UUID user);

}
