package com.api.tourplanner.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record JourneyInformation(
        @Id
        Long id,
        @NotBlank
        String city,
        @NotNull
        LocalDate fromDate,
        LocalDate toDate
) {}
