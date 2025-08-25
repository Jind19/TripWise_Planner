package com.tripwise.tripplanner.service;

import com.tripwise.tripplanner.model.*;
import com.tripwise.tripplanner.error.NotFoundException;
import com.tripwise.tripplanner.repo.*;
import com.tripwise.tripplanner.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackingListService {
    private final TripRepository trips;
    private final PackingTemplateRepository templates;
    private final UserPackingListRepository userLists;
    private final PackingListItemRepository itemsRepo;

    @Transactional
    public UserPackingList cloneTemplateToTrip(UUID templateId, UUID tripId) {
        var trip = trips.findById(tripId).orElseThrow(() -> new NotFoundException("Trip not found"));
        var userId = SecurityUtils.currentUserId();
        if (!trip.getUserId().equals(userId)) throw new NotFoundException("Trip not found");

        var existing = userLists.findByTripAndUserId(trip, userId).orElse(null);
        if (existing != null) return existing; // idempotent

        var template = templates.findById(templateId).orElseThrow(() -> new NotFoundException("Template not found"));

        var upl = UserPackingList.builder()
                .trip(trip)
                .userId(userId)
                .templateNameSnapshot(template.getName())
                .build();

        template.getItems().forEach(ti -> {
            var pli = PackingListItem.builder()
                    .packingList(upl)
                    .name(ti.getName())
                    .quantity(ti.getDefaultQuantity())
                    .packed(false)
                    .build();
            upl.getItems().add(pli);
        });

        return userLists.save(upl);
    }

    @Transactional(readOnly = true)
    public UserPackingList getUserListForTrip(UUID tripId) {
        var trip = trips.findById(tripId).orElseThrow(() -> new NotFoundException("Trip not found"));
        var userId = SecurityUtils.currentUserId();
        return userLists.findByTripAndUserId(trip, userId)
                .orElseThrow(() -> new NotFoundException("Packing list not found for this trip"));
    }

    @Transactional
    public PackingListItem updateItem(UUID listId, UUID itemId, String name, int qty, boolean packed, String notes) {
        var upl = userLists.findById(listId).orElseThrow(() -> new NotFoundException("Packing list not found"));
        if (!upl.getUserId().equals(SecurityUtils.currentUserId())) throw new NotFoundException("Packing list not found");

        // Ensure item belongs to this list
        var item = itemsRepo.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        if (!item.getPackingList().getId().equals(listId)) throw new NotFoundException("Item not found");

        item.setName(name);
        item.setQuantity(qty);
        item.setPacked(packed);
        item.setNotes(notes);
        return itemsRepo.save(item);
    }
}

