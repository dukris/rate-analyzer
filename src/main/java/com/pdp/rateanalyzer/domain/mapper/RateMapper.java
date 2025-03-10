package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.domain.Rate;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapper {

  Rate toDomain(RateDto dto);

  List<Rate> toDomain(List<RateDto> preferences);

}
