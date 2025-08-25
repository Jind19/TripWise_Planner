package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.model.Trip;
import com.tripwise.tripplanner.error.NotFoundException;
import com.tripwise.tripplanner.repo.TripRepository;
import com.tripwise.tripplanner.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository trips;

    @Transactional
    public Trip create(Trip t) {
        t.setUserId(SecurityUtils.currentUserId());
        return trips.save(t);
    }

    @Transactional(readOnly = true)
    public Trip getOwned(UUID id) {
        var trip = trips.findById(id).orElseThrow(() -> new NotFoundException("Trip not found"));
        if (!trip.getUserId().equals(SecurityUtils.currentUserId())) {
            throw new NotFoundException("Trip not found"); // hide existence
        }
        return trip;
    }
}
