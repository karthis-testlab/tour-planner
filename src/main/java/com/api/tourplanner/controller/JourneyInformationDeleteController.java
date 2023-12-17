package com.api.tourplanner.controller;

import com.api.tourplanner.repository.JourneyCore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/journey")
public class JourneyInformationDeleteController {

    private final JourneyCore journeyCore;

    public JourneyInformationDeleteController(JourneyCore journeyCore) {
        this.journeyCore = journeyCore;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/detail/delete/{id}")
    private void deleteExistingJourneyInformation(@PathVariable Long id) {
        journeyCore.deleteById(id);
    }

}
