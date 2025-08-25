package com.tripwise.tripplanner.controller;

import com.tripwise.tripplanner.dto.PackingListDtos;
import com.tripwise.tripplanner.service.PackingListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tripplanner")
@RequiredArgsConstructor
public class PackingListController {

    private final PackingListService packingListService;

    @PostMapping("/packing-templates/{templateId}/clone")
    @ResponseStatus(HttpStatus.CREATED)
    public PackingListDtos.ListRes cloneTemplate(@PathVariable UUID templateId,
                                                 @RequestParam UUID tripId) {
        var list = packingListService.cloneTemplateToTrip(templateId, tripId);
        return new PackingListDtos.ListRes(
                list.getId(),
                list.getTrip().getId(),
                list.getTemplateNameSnapshot(),
                list.getItems().stream().map(i ->
                        new PackingListDtos.ItemRes(i.getId(), i.getName(), i.getQuantity(), i.isPacked(), i.getNotes())
                ).toList()
        );
    }

    @GetMapping("/user-packing-lists/{tripId}")
    public PackingListDtos.ListRes getForTrip(@PathVariable UUID tripId) {
        var list = packingListService.getUserListForTrip(tripId);
        return new PackingListDtos.ListRes(
                list.getId(),
                list.getTrip().getId(),
                list.getTemplateNameSnapshot(),
                list.getItems().stream().map(i ->
                        new PackingListDtos.ItemRes(i.getId(), i.getName(), i.getQuantity(), i.isPacked(), i.getNotes())
                ).toList()
        );
    }

    @PutMapping("/user-packing-lists/{listId}/items/{itemId}")
    public PackingListDtos.ItemRes updateItem(@PathVariable UUID listId,
                                              @PathVariable UUID itemId,
                                              @Valid @RequestBody PackingListDtos.ItemUpdateReq body) {
        var item = packingListService.updateItem(listId, itemId, body.name(), body.quantity(), body.packed(), body.notes());
        return new PackingListDtos.ItemRes(item.getId(), item.getName(), item.getQuantity(), item.isPacked(), item.getNotes());
    }
}

