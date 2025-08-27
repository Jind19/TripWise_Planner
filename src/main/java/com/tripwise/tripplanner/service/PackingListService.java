package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.dto.PackingDtos;
import com.tripwise.tripplanner.model.*;
import com.tripwise.tripplanner.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList; import java.util.List;

@Service @RequiredArgsConstructor
public class PackingListService {
    private final TripPackingItemRepository tripItems;
    private final PackingTemplateRepository templates;


    @Transactional
    public List<TripPackingItem> assignTemplates(Trip trip, PackingDtos.AssignTemplatesReq req){
        int people = trip.getNumPeople();
        List<TripPackingItem> created = new ArrayList<>();
        for (Long tid : req.templateIds()) {
            PackingTemplate t = templates.findById(tid).orElseThrow();
            t.getItems().forEach(item -> {
                int qty = Math.max(0, item.getQtyPerPerson()) * people;
                created.add(tripItems.save(TripPackingItem.builder()
                        .trip(trip)
                        .templateItemId(item.getId())
                        .name(item.getName())
                        .quantity(qty)
                        .checked(false)
                        .build()));
            });
        }
        return created;
    }

    public List<TripPackingItem> list(Long tripId){
        return tripItems.findByTripIdOrderByNameAsc(tripId);
    }
}
