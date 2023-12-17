package com.api.tourplanner.controller;

import com.api.tourplanner.exceptions.JourneyInformationReadException;
import com.api.tourplanner.model.JourneyInformation;
import com.api.tourplanner.repository.JourneyCore;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/journey")
public class JourneyInformationUpdateController {

    private final JourneyCore journeyCore;

    public JourneyInformationUpdateController(JourneyCore journeyCore) {
        this.journeyCore = journeyCore;
    }

    @PutMapping("/detail/update/{id}")
    private JourneyInformation updateTheExistingJourneyInformation(@PathVariable Long id, @RequestBody JourneyInformation journeyInformation) {
        Optional<JourneyInformation> existingRecord = journeyCore.findById(id);
        if(existingRecord.isPresent()) {
            JourneyInformation update = new JourneyInformation(
                    existingRecord.get().id(),
                    journeyInformation.city(),
                    journeyInformation.fromDate(),
                    journeyInformation.toDate()
            );
            return journeyCore.save(update);
        } else {
            throw new JourneyInformationReadException();
        }
    }




}
