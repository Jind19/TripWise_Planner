package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.dto.TripDtos;
import com.tripwise.tripplanner.model.Trip;
import com.tripwise.tripplanner.repo.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class TripService {
    private final TripRepository trips;


    public Trip create(TripDtos.CreateReq req){
        Trip t = Trip.builder()
                .destination(req.destination())
                .startDate(req.startDate())
                .endDate(req.endDate())
                .numPeople(req.numPeople())
                .budget(req.budget())
                .status("PLANNING")
                .build();
        return trips.save(t);
    }

    public List<Trip> list(){
        return trips.findAllByOrderByStartDateDesc();
    }

    public Trip get(Long id){
        return trips.findById(id).orElseThrow();
    }
}
