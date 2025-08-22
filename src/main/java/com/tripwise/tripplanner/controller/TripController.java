package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.model.Trip;
import com.tripwise.tripplanner.security.AuthUtils;
import com.tripwise.tripplanner.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tripplanner/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;


    @PostMapping
    public ResponseEntity<TripDtos.TripResponse> createTrip(@Valid @RequestBody TripDtos.CreateTripRequest req){
        String ownerId = AuthUtils.userIdFromSecurityContext();
        Trip t = tripService.createTrip(ownerId, req);
        return ResponseEntity.ok(TripMapper.toTripResponse(t));
    }


    @GetMapping("/{tripId}")
    public ResponseEntity<TripDtos.TripResponse> getTrip(@PathVariable Long tripId){
        String ownerId = AuthUtils.userIdFromSecurityContext();
        Trip t = tripService.getOwnedTrip(ownerId, tripId);
        return ResponseEntity.ok(TripMapper.toTripResponse(t));
    }
}
