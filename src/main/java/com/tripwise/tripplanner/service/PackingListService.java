package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.dto.PackingDtos;
import com.tripwise.tripplanner.model.*;
import com.tripwise.tripplanner.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackingListService {

    private final PackingTemplateRepository templates;
    private final TripPackingItemRepository tripItems;

    /**
     * Assign templates to a trip → expand template items into trip-specific items.
     */
    @Transactional
    public List<TripPackingItem> assignTemplates(Trip trip, PackingDtos.AssignTemplatesReq req) {
        List<TripPackingItem> newItems = new ArrayList<>();

        for (Long templateId : req.templateIds()) {
            PackingTemplate template = templates.findById(templateId).orElseThrow();

            for (PackingTemplateItem ti : template.getItems()) {
                TripPackingItem newItem = TripPackingItem.builder()
                        .name(ti.getName())
                        .quantity(ti.getQtyPerPerson() * trip.getNumPeople())
                        .checked(false)
                        .build();

                // 👇 Use helper to maintain both sides of the relationship
                trip.addItem(newItem);
                newItems.add(newItem);
            }
        }

        // Save all items via repo (since cascade handles Trip->Items, saving Trip would also work)
        return tripItems.saveAll(newItems);
    }
}