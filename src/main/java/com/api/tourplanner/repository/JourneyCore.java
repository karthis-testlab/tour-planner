package com.api.tourplanner.repository;

import com.api.tourplanner.model.JourneyInformation;
import org.springframework.data.repository.CrudRepository;

public interface JourneyCore extends CrudRepository<JourneyInformation, Long> {
}
