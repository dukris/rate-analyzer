package com.pdp.rateanalyzer.api;

import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.CreatePreferenceDto;
import com.pdp.rateanalyzer.api.dto.PreferenceDto;
import com.pdp.rateanalyzer.domain.mapper.PreferenceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/preferences")
@Tag(name = "Controller for preferences")
public class PreferenceController {

  private final PreferencePersistenceAdapter preferencePersistenceAdapter;
  private final PreferenceMapper mapper;

  @GetMapping
  @Operation(summary = "Get preferences by user")
  public List<PreferenceDto> getAllByUser(@RequestParam UUID user) {
    return mapper.toDto(preferencePersistenceAdapter.getAllByUser(user));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Save preferences")
  public PreferenceDto save(@RequestBody @Valid CreatePreferenceDto dto) {
    return mapper.toDto(preferencePersistenceAdapter.save(mapper.toEntity(dto)));
  }

}
