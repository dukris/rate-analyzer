package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.api.dto.CreatePreferenceDto;
import com.pdp.rateanalyzer.api.dto.PreferenceDto;
import com.pdp.rateanalyzer.domain.Preference;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreferenceMapper {

  Preference toEntity(PreferenceDto dto);

  Preference toEntity(CreatePreferenceDto dto);

  PreferenceDto toDto(Preference preference);

  List<PreferenceDto> toDto(List<Preference> preferences);

}
