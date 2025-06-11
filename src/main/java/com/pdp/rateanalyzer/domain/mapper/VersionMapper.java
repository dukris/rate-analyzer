package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.domain.Version;
import com.pdp.rateanalyzer.domain.VersionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VersionMapper {

  VersionEntity toEntity(Version model);

  Version toModel(VersionEntity entity);

}
