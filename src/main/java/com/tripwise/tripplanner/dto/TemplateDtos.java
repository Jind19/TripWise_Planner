package com.tripwise.tripplanner.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class TemplateDtos {
    public record ItemReq(@NotBlank String name, @Min(0) Integer qtyPerPerson, String notes) {}
    public record CreateReq(@NotBlank String name, String description, List<ItemReq> items) {}
    public record UpdateReq(@NotBlank String name, String description, List<ItemReq> items, Boolean active) {}


    public record ItemRes(Long id, String name, Integer defaultQuantity) {}
    public record TemplateRes(Long id, String name, String description, boolean active, int version, List<ItemRes> items) {}
}
