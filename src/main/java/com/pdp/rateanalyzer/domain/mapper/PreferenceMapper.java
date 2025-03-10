package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.api.dto.CreatePreferenceDto;
import com.pdp.rateanalyzer.api.dto.PreferenceDto;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreferenceMapper {

  PreferenceEntity toEntity(PreferenceDto dto);

  PreferenceEntity toEntity(CreatePreferenceDto dto);

  PreferenceDto toDto(PreferenceEntity preference);

  List<PreferenceDto> toDto(List<PreferenceEntity> preferences);

}
