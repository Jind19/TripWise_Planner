package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.PackingDtos;
import com.tripwise.tripplanner.model.Trip; import com.tripwise.tripplanner.model.TripPackingItem;
import com.tripwise.tripplanner.service.PackingListService; import com.tripwise.tripplanner.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/trips/{tripId}/packing-list") @RequiredArgsConstructor
public class PackingListController {
    private final TripService tripService; private final PackingListService packing;

    @PostMapping("/from-templates")
    public PackingDtos.PackingListRes assign(@PathVariable Long tripId, @Valid @RequestBody PackingDtos.AssignTemplatesReq req){
        Trip t = tripService.getOwned(tripId);
        List<TripPackingItem> items = packing.assignTemplates(t, req);
        return new PackingDtos.PackingListRes(items.stream().map(i -> new PackingDtos.PackingItemRes(i.getId(), i.getName(), i.getQuantity(), i.isChecked())).toList());
    }

    @GetMapping
    public PackingDtos.PackingListRes list(@PathVariable Long tripId){
        Trip t = tripService.getOwned(tripId);
        List<TripPackingItem> items = packing.list(t.getId());
        return new PackingDtos.PackingListRes(items.stream().map(i -> new PackingDtos.PackingItemRes(i.getId(), i.getName(), i.getQuantity(), i.isChecked())).toList());
    }
}
