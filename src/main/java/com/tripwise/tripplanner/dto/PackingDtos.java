package com.tripwise.tripplanner.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class PackingDtos {
    public record AssignTemplatesReq(@NotEmpty List<Long> templateIds) {
    }

    public record PackingItemRes(Long id, String name, Integer quantity, boolean checked) {
    }

    public record PackingListRes(List<PackingItemRes> items) {
    }
}
