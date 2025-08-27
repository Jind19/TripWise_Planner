package com.tripwise.tripplanner.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TripDtos {
    public record CreateReq(
            @NotBlank String destination,
            @NotNull LocalDate startDate,
            @NotNull LocalDate endDate,
            @Min(1) Integer numPeople,
            @DecimalMin("0.0") BigDecimal budget
    ) {
    }

    public record TripRes(Long id, String destination, LocalDate startDate, LocalDate endDate, Integer numPeople,
                          BigDecimal budget, String status) {
    }

    public record ListRes(List<TripRes> trips) {
    }
}
