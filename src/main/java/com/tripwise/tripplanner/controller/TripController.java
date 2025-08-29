package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.TripDtos;
import com.tripwise.tripplanner.model.Trip;
import com.tripwise.tripplanner.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/trips") @RequiredArgsConstructor
public class TripController {
    private final TripService tripService;


    @GetMapping
    public List<TripDtos.TripRes> list(){
        return tripService.list().stream()
                .map(t -> new TripDtos.TripRes(t.getId(), t.getDestination(), t.getStartDate(), t.getEndDate(), t.getNumPeople(), t.getBudget(), t.getStatus()))
                .toList();
    }


    @PostMapping
    public TripDtos.TripRes create(@Valid @RequestBody TripDtos.CreateReq req){
        Trip t = tripService.create(req);
        return new TripDtos.TripRes(t.getId(), t.getDestination(), t.getStartDate(), t.getEndDate(), t.getNumPeople(), t.getBudget(), t.getStatus());
    }


    @GetMapping("/{id}")
    public TripDtos.TripRes get(@PathVariable Long id){
        Trip t = tripService.get(id);
        return new TripDtos.TripRes(t.getId(), t.getDestination(), t.getStartDate(), t.getEndDate(), t.getNumPeople(), t.getBudget(), t.getStatus());
    }
}
