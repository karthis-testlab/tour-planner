package com.api.tourplanner.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record JourneyInformation(@Id Long id, String city, LocalDate fromDate, LocalDate toDate) {
}
