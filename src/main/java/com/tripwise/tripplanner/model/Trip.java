package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "trips", indexes = @Index(name = "idx_trip_user", columnList = "userId"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Trip extends BaseEntity {
    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @NotBlank @Column(nullable = false)
    private String destinationCountry;

    @NotBlank @Column(nullable = false)
    private String destinationCity;

    @NotNull @Column(nullable = false)
    private LocalDate startDate;

    @NotNull @Column(nullable = false)
    private LocalDate endDate;

    @Min(1) @Column(nullable = false)
    private int peopleCount;

    @Digits(integer = 12, fraction = 2)
    @Column(precision = 14, scale = 2)
    private BigDecimal budget;
}
