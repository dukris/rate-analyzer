package com.pdp.rateanalyzer.adapter.repository;

import com.pdp.rateanalyzer.domain.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<VersionEntity, Long> {

}
