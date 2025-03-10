package com.pdp.rateanalyzer.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.CreatePreferenceDto;
import com.pdp.rateanalyzer.api.dto.PreferenceDto;
import com.pdp.rateanalyzer.domain.PreferenceEntity;
import com.pdp.rateanalyzer.domain.mapper.PreferenceMapper;
import com.pdp.rateanalyzer.extensions.FakeCreatePreferenceDto;
import com.pdp.rateanalyzer.extensions.FakePreferenceEntity;
import com.pdp.rateanalyzer.extensions.FakePreferenceDto;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest(controllers = PreferenceController.class)
class PreferenceControllerTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PreferencePersistenceAdapter adapter;
  @MockBean
  private PreferenceMapper mapper;

  @Test
  @ExtendWith({FakePreferenceEntity.class, FakePreferenceDto.class})
  void shouldReturnPreferencesByUser(PreferenceEntity preference, PreferenceDto response) throws Exception {
    // given
    UUID user = UUID.randomUUID();
    when(adapter.getAllByUser(user)).thenReturn(List.of(preference));
    when(mapper.toDto(List.of(preference))).thenReturn(List.of(response));

    // when
    this.mvc.perform(get("/api/v1/preferences")
            .param("user", user.toString())
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].userId").value(response.getUserId().toString()))
        .andExpect(jsonPath("$[0].currencyId").value(response.getCurrencyId().toString()))
        .andExpect(jsonPath("$[0].rate").value(response.getRate()));
  }

  @Test
  @ExtendWith({FakePreferenceEntity.class, FakeCreatePreferenceDto.class, FakePreferenceDto.class})
  void shouldSavePreferences(PreferenceEntity preference, CreatePreferenceDto request, PreferenceDto response) throws Exception {
    // given
    when(mapper.toEntity(request)).thenReturn(preference);
    when(adapter.save(preference)).thenReturn(preference);
    when(mapper.toDto(preference)).thenReturn(response);

    // when
    this.mvc.perform(post("/api/v1/preferences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("$.userId").value(response.getUserId().toString()))
        .andExpect(jsonPath("$.currencyId").value(response.getCurrencyId().toString()))
        .andExpect(jsonPath("$.rate").value(response.getRate()));
  }

  @Test
  void shouldReturnBadRequestWhenInvalidRequest() throws Exception {
    // given
    CreatePreferenceDto request = new CreatePreferenceDto();

    // when
    mvc.perform(post("/api/v1/preferences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

}
