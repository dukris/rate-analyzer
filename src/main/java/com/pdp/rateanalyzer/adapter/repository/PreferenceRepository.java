package com.pdp.rateanalyzer.adapter.repository;

import com.pdp.rateanalyzer.domain.PreferenceEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<PreferenceEntity, UUID> {

  Stream<PreferenceEntity> streamAllBy();

  List<PreferenceEntity> findAllByUserId(UUID user);

}
