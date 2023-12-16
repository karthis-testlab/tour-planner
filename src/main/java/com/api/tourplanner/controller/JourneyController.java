package com.api.tourplanner.controller;

import com.api.tourplanner.model.JourneyInformation;
import com.api.tourplanner.repository.JourneyCore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey")
public class JourneyController {

    private final JourneyCore journeyCore;

    public JourneyController(JourneyCore journeyCore) {
        this.journeyCore = journeyCore;
    }

    @GetMapping("/details")
    private Iterable<JourneyInformation> getAllJourneyInformations() {
        return journeyCore.findAll();
    }
}
