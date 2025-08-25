// dto/PackingListDtos.java
package com.tripwise.tripplanner.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public class PackingListDtos {

    public record ListRes(
            UUID id,
            UUID tripId,
            String templateNameSnapshot,
            List<ItemRes> items
    ) {}

    public record ItemRes(
            UUID id,
            String name,
            int quantity,
            boolean packed,
            String notes
    ) {}

    public record ItemUpdateReq(
            @NotBlank String name,
            @Min(0) int quantity,
            boolean packed,
            String notes
    ) {}
}
