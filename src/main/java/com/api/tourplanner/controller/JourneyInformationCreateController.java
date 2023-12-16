package com.api.tourplanner.controller;

import com.api.tourplanner.model.JourneyInformation;
import com.api.tourplanner.repository.JourneyCore;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journey")
public class JourneyInformationCreateController {

    private final JourneyCore journeyCore;

    public JourneyInformationCreateController(JourneyCore journeyCore) {
        this.journeyCore = journeyCore;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/detail/enter")
    private JourneyInformation createNewJourneyInformation(@RequestBody @Validated JourneyInformation journeyInformation) {
        return journeyCore.save(journeyInformation);
    }

}
