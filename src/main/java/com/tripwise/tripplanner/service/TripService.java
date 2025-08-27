package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.dto.TripDtos;
import com.tripwise.tripplanner.model.Trip;
import com.tripwise.tripplanner.repo.TripRepository;
import com.tripwise.tripplanner.security.SecurityUtil;
import com.tripwise.tripplanner.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository trips;


    public Trip create(TripDtos.CreateReq req) {
        UserPrincipal me = SecurityUtil.currentUser();
        Trip t = Trip.builder()
                .ownerSub(me.sub())
                .ownerEmail(me.email())
                .destination(req.destination())
                .startDate(req.startDate())
                .endDate(req.endDate())
                .numPeople(req.numPeople())
                .budget(req.budget())
                .status("PLANNING")
                .build();
        return trips.save(t);
    }

    public List<Trip> listMine() {
        UserPrincipal me = SecurityUtil.currentUser();
        return trips.findByOwnerSubOrderByStartDateDesc(me.sub());
    }

    public Trip getOwned(Long id) {
        UserPrincipal me = SecurityUtil.currentUser();
        Trip t = trips.findById(id).orElseThrow();
        if (!t.getOwnerSub().equals(me.sub())) throw new IllegalArgumentException("Not your trip");
        return t;
    }
}
