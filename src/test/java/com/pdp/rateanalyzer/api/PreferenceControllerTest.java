package com.pdp.rateanalyzer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.rateanalyzer.adapter.PreferencePersistenceAdapter;
import com.pdp.rateanalyzer.api.dto.CreatePreferenceDto;
import com.pdp.rateanalyzer.api.dto.PreferenceDto;
import com.pdp.rateanalyzer.domain.Preference;
import com.pdp.rateanalyzer.domain.mapper.PreferenceMapper;
import com.pdp.rateanalyzer.extensions.FakeCreatePreferenceDto;
import com.pdp.rateanalyzer.extensions.FakePreference;
import com.pdp.rateanalyzer.extensions.FakePreferenceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  @ExtendWith({FakePreference.class, FakePreferenceDto.class})
  void shouldReturnPreferencesByUser(Preference preference, PreferenceDto response) throws Exception {
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
        .andExpect(jsonPath("$[0].currency").value(response.getCurrency()))
        .andExpect(jsonPath("$[0].rate").value(response.getRate()));
  }

  @Test
  @ExtendWith({FakePreference.class, FakeCreatePreferenceDto.class, FakePreferenceDto.class})
  void shouldSavePreferences(Preference preference, CreatePreferenceDto request, PreferenceDto response) throws Exception {
    // given
    when(mapper.toModel(request)).thenReturn(preference);
    when(adapter.save(preference)).thenReturn(preference);
    when(mapper.toDto(preference)).thenReturn(response);

    // when
    this.mvc.perform(post("/api/v1/preferences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("$.userId").value(response.getUserId().toString()))
        .andExpect(jsonPath("$.currency").value(response.getCurrency()))
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
