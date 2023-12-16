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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JourneyInformationCreateController.class)
@AutoConfigureMockMvc
public class JourneyInformationCreateTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JourneyCore journeyCore;


    @Test
    void shouldCreateNewJourneyInformation() throws Exception {

        JourneyInformation newEntry = new JourneyInformation(2L, "Madurai", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12,15));

        when(journeyCore.save(newEntry)).thenReturn(newEntry);

        String mockPayload = """
                {
                  "id": 2,
                  "city": "Madurai",
                  "fromDate": "2023-12-12",
                  "toDate": "2023-12-15"
                }
                """;

        mockMvc.perform(post("/journey/detail/enter")
                .contentType("application/json")
                .content(mockPayload))
                .andExpect(status().isCreated());

    }


}
