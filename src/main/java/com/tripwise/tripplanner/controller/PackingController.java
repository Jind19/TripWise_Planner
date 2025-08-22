package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.model.PackingTemplate;
import com.tripwise.tripplanner.model.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tripplanner")
@RequiredArgsConstructor
public class PackingController {
    private final PackingService packingService;
    private final TripService tripService;


    @GetMapping("/packing-templates")
    public ResponseEntity<List<TripDtos.PackingTemplateSummary>> listTemplates() {
        return ResponseEntity.ok(
                packingService.listTemplates().stream().map(TripMapper::toTemplateSummary).toList()
        );
    }


    @PostMapping("/packing-templates/{templateId}/clone")
    public ResponseEntity<TripDtos.PackingListResponse> cloneTemplate(@PathVariable Long templateId,
                                                                      @RequestParam Long tripId) {
        String ownerId = AuthUtils.userIdFromSecurityContext();
        Trip trip = tripService.getOwnedTrip(ownerId, tripId);
        PackingTemplate template = packingService.getTemplateById(templateId);
        PackingList list = packingService.cloneTemplateToTrip(ownerId, template, trip);
        return ResponseEntity.ok(TripMapper.toPackingListResponse(list));
    }


    @GetMapping("/user-packing-lists/{tripId}")
    public ResponseEntity<TripDtos.PackingListResponse> getUserList(@PathVariable Long tripId) {
        String ownerId = AuthUtils.userIdFromSecurityContext();
        Trip trip = tripService.getOwnedTrip(ownerId, tripId);
        PackingList list = packingService.getUserPackingList(ownerId, trip);
        return ResponseEntity.ok(TripMapper.toPackingListResponse(list));
    }


    @PutMapping("/user-packing-lists/{listId}/items/{itemId}")
    public ResponseEntity<TripDtos.PackingItemDto> updateItem(@PathVariable Long listId,
                                                              @PathVariable Long itemId,
                                                              @Valid @RequestBody TripDtos.UpdatePackingItemRequest req) {
        String ownerId = AuthUtils.userIdFromSecurityContext();
        var item = packingService.updateItemPacked(ownerId, itemId, req.getPacked());
        return ResponseEntity.ok(TripMapper.toPackingItem(item));
    }
}
