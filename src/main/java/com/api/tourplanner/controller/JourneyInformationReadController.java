package com.api.tourplanner.controller;

import com.api.tourplanner.exceptions.JourneyInformationReadException;
import com.api.tourplanner.model.JourneyInformation;
import com.api.tourplanner.repository.JourneyCore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/journey")
public class JourneyInformationReadController {

    private final JourneyCore journeyCore;

    public JourneyInformationReadController(JourneyCore journeyCore) {
        this.journeyCore = journeyCore;
    }

    @GetMapping("/details")
    private Iterable<JourneyInformation> getAllJourneyInformations() {
        return journeyCore.findAll();
    }

    @GetMapping("/detail/{id}")
    private Optional<JourneyInformation> getIndividualJourneyInformation(@PathVariable Long id) {
        Optional<JourneyInformation> journeyInformation = Optional.ofNullable(journeyCore.findById(id)
                                                                            .orElseThrow(JourneyInformationReadException::new));
        return journeyInformation;
    }

}
