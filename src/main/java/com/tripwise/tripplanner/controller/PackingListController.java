package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.PackingDtos;
import com.tripwise.tripplanner.model.Trip; import com.tripwise.tripplanner.model.TripPackingItem;
import com.tripwise.tripplanner.repo.TripPackingItemRepository;
import com.tripwise.tripplanner.service.PackingListService; import com.tripwise.tripplanner.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/trips/{tripId}/packing-list") @RequiredArgsConstructor
public class PackingListController {
    private final TripService tripService;
    private final PackingListService packing;
    private final TripPackingItemRepository tripItems;


    @PostMapping("/from-templates")
    public PackingDtos.PackingListRes assign(@PathVariable Long tripId, @Valid @RequestBody PackingDtos.AssignTemplatesReq req){
        Trip t = tripService.get(tripId);
        List<TripPackingItem> items = packing.assignTemplates(t, req);
        return new PackingDtos.PackingListRes(items.stream().map(i -> new PackingDtos.PackingItemRes(i.getId(), i.getName(), i.getQuantity(), i.isChecked())).toList());
    }


    @GetMapping
    public PackingDtos.PackingListRes list(@PathVariable Long tripId){
        Trip t = tripService.get(tripId);
        List<TripPackingItem> items = packing.list(t.getId());
        return new PackingDtos.PackingListRes(items.stream().map(i -> new PackingDtos.PackingItemRes(i.getId(), i.getName(), i.getQuantity(), i.isChecked())).toList());
    }

    @PostMapping
    public PackingDtos.PackingItemRes addItem(
            @PathVariable Long tripId,
            @Valid @RequestBody PackingDtos.PackingItemRes req) {

        Trip t = tripService.get(tripId);
        TripPackingItem item = tripItems.save(
                TripPackingItem.builder()
                        .trip(t)
                        .name(req.name())
                        .quantity(req.quantity())
                        .checked(req.checked())
                        .build()
        );
        return new PackingDtos.PackingItemRes(item.getId(), item.getName(), item.getQuantity(), item.isChecked());
    }

    // Update existing item
    @PutMapping("/{itemId}")
    public PackingDtos.PackingItemRes updateItem(
            @PathVariable Long tripId,
            @PathVariable Long itemId,
            @Valid @RequestBody PackingDtos.PackingItemRes req) {

        TripPackingItem item = tripItems.findById(itemId).orElseThrow();
        item.setName(req.name());
        item.setQuantity(req.quantity());
        item.setChecked(req.checked());
        item = tripItems.save(item);
        return new PackingDtos.PackingItemRes(item.getId(), item.getName(), item.getQuantity(), item.isChecked());
    }

    // Delete an item
    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable Long tripId, @PathVariable Long itemId) {
        tripItems.deleteById(itemId);
    }
}
