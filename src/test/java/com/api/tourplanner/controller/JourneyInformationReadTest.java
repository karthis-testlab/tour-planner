package com.api.tourplanner.controller;

import com.api.tourplanner.exceptions.JourneyInformationReadException;
import com.api.tourplanner.model.JourneyInformation;
import com.api.tourplanner.repository.JourneyCore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(JourneyInformationReadController.class)
@AutoConfigureMockMvc
public class JourneyInformationReadTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JourneyCore journeyCore;

    static List<JourneyInformation> journeyInformations = new ArrayList<JourneyInformation>();
    static JourneyInformation journeyInformation;

    @BeforeAll
    static void setUp() {
        journeyInformations.add(new JourneyInformation(
                0L,
                "Budapest",
                LocalDate.of(2023, 12, 22),
                LocalDate.of(2024, 01, 02)));

        journeyInformations.add(new JourneyInformation(
                1L,
                "Halstat",
                LocalDate.of(2023, 12, 20),
                LocalDate.of(2023, 12, 21)));

        journeyInformation = new JourneyInformation(
                                         0L,
                                       "Budapest",
                                            LocalDate.of(2023, 12, 22),
                                            LocalDate.of(2024, 01, 02));
    }

    @Test
    void shouldReturnSingleJourneyDetailObject() throws Exception {

        String mockJsonResponse = """
                 {
                   "id": 0,
                   "city": "Budapest",
                   "fromDate": "2023-12-22",
                   "toDate": "2024-01-02"
                 }                
                 """;
        
        when(journeyCore.findById(0L)).thenReturn(Optional.ofNullable(journeyInformation));
        
        mockMvc.perform(get("/journey/detail/0"))
                .andExpect(status().isOk())
                .andExpect(content().json(mockJsonResponse));
    }

    @Test
    void shouldReturnAllJourneyInformations() throws Exception {

        String mockJsonResponses = """
                [
                   {
                      "id":0,
                      "city":"Budapest",
                      "fromDate":"2023-12-22",
                      "toDate":"2024-01-02"
                   },
                   {
                      "id":1,
                      "city":"Halstat",
                      "fromDate":"2023-12-20",
                      "toDate":"2023-12-21"
                   }
                ]
                """;

        when(journeyCore.findAll()).thenReturn(journeyInformations);

        mockMvc.perform(get("/journey/details"))
                .andExpect(status().isOk())
                .andExpect(content().json(mockJsonResponses));

    }

    @Test
    void shouldNotReturnJourneyInformationForInVaildId() throws Exception {

        when(journeyCore.findById(999L)).thenThrow(JourneyInformationReadException.class);

        mockMvc.perform(get("/journey/detail/999"))
                .andExpect(status().isNotFound());
    }

}
