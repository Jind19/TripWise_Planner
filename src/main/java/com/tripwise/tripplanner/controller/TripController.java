package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.model.Trip;
import com.tripwise.tripplanner.dto.TripDtos;
import com.tripwise.tripplanner.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tripplanner/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripDtos.Res create(@Valid @RequestBody TripDtos.CreateReq body) {
        var t = Trip.builder()
                .destinationCountry(body.destinationCountry())
                .destinationCity(body.destinationCity())
                .startDate(body.startDate())
                .endDate(body.endDate())
                .peopleCount(body.peopleCount())
                .budget(body.budget())
                .build();
        var saved = tripService.create(t);
        return new TripDtos.Res(
                saved.getId(), saved.getDestinationCountry(), saved.getDestinationCity(),
                saved.getStartDate(), saved.getEndDate(), saved.getPeopleCount(), saved.getBudget()
        );
    }

    @GetMapping("/{tripId}")
    public TripDtos.Res get(@PathVariable UUID tripId) {
        var t = tripService.getOwned(tripId);
        return new TripDtos.Res(
                t.getId(), t.getDestinationCountry(), t.getDestinationCity(),
                t.getStartDate(), t.getEndDate(), t.getPeopleCount(), t.getBudget()
        );
    }
}
