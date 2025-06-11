package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.api.dto.CreatePreferenceDto;
import com.pdp.rateanalyzer.api.dto.PreferenceDto;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface PreferenceMapper {

  Preference toModel(PreferenceEntity entity);

  Stream<Preference> toModel(Stream<PreferenceEntity> entities);

  List<Preference> toModel(List<PreferenceEntity> entities);

  Preference toModel(CreatePreferenceDto dto);

  PreferenceEntity toEntity(Preference model);

  PreferenceDto toDto(Preference model);

  List<PreferenceDto> toDto(List<Preference> models);

}
