package com.api.tourplanner.controller;

import com.api.tourplanner.model.JourneyInformation;
import com.api.tourplanner.repository.JourneyCore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JourneyInformationUpdateController.class)
@AutoConfigureMockMvc

public class JourneyInformationUpdateTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JourneyCore journeyCore;

    @Test
    void shouldUpdateJouneryInformationWithGiveValidInput() throws Exception {

        JourneyInformation update = new JourneyInformation(1L, "Vienna", LocalDate.of(2023, 12, 18), LocalDate.of(2023, 12,22));
        when(journeyCore.findById(1L)).thenReturn(Optional.of(update));
        when(journeyCore.save(update)).thenReturn(update);

        String mockPayloadSchema = """
                {
                  "id": %d,
                  "city": "%s",
                  "fromDate": "%s",
                  "toDate": "%s"
                }
                """;

        String mockPayload = mockPayloadSchema.formatted(update.id(), update.city(), update.fromDate(), update.toDate());

        mockMvc.perform(put("/journey/detail/update/1")
                        .contentType("application/json")
                        .content(mockPayload))
                .andExpect(status().isOk());

    }

    @Test
    void shouldUpdateJouneryInformationWithGiveInValidId() throws Exception {

        JourneyInformation update = new JourneyInformation(1L, "Vienna", LocalDate.of(2023, 12, 18), LocalDate.of(2023, 12,22));
        when(journeyCore.findById(1L)).thenReturn(Optional.of(update));
        when(journeyCore.save(update)).thenReturn(update);

        String mockPayloadSchema = """
                {
                  "id": %d,
                  "city": "%s",
                  "fromDate": "%s",
                  "toDate": "%s"
                }
                """;

        String mockPayload = mockPayloadSchema.formatted(update.id(), update.city(), update.fromDate(), update.toDate());

        mockMvc.perform(put("/journey/detail/update/2")
                        .contentType("application/json")
                        .content(mockPayload))
                .andExpect(status().isNotFound());

    }

}
