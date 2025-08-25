// dto/TripDtos.java
package com.tripwise.tripplanner.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TripDtos {

    public record CreateReq(
            @NotBlank String destinationCountry,
            @NotBlank String destinationCity,
            @NotNull LocalDate startDate,
            @NotNull LocalDate endDate,
            @Min(1) int peopleCount,
            @Digits(integer = 12, fraction = 2) BigDecimal budget
    ) {}

    public record Res(
            UUID id,
            String destinationCountry,
            String destinationCity,
            LocalDate startDate,
            LocalDate endDate,
            int peopleCount,
            BigDecimal budget
    ) {}
}
