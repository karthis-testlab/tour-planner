package com.api.tourplanner.controller;

import com.api.tourplanner.repository.JourneyCore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JourneyInformationDeleteController.class)
@AutoConfigureMockMvc
public class JourneyInformationDeleteTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JourneyCore journeyCore;

    @Test
    void shouldDeleteJourneyInformationWithValidId() throws Exception {

        doNothing().when(journeyCore).deleteById(1L);

        mockMvc.perform(delete("/journey/detail/delete/1"))
                .andExpect(status().isNoContent());

    }

}
