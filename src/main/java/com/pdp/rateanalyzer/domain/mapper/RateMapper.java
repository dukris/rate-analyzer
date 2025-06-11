package com.pdp.rateanalyzer.domain.mapper;

import com.pdp.rateanalyzer.api.dto.RateDto;
import com.pdp.rateanalyzer.domain.Rate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RateMapper {

  Rate toModel(RateDto dto);

  List<Rate> toModel(List<RateDto> dtos);

}
