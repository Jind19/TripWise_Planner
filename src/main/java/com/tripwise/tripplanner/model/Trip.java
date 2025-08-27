package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal; import java.time.LocalDate;


@Entity @Table(name = "trips")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Trip extends BaseEntity {
    @Column(nullable=false) private String ownerSub; // JWT subject
    @Column(nullable=false) private String ownerEmail; // from JWT
    @Column(nullable=false) private String destination;
    @Column(nullable=false) private LocalDate startDate;
    @Column(nullable=false) private LocalDate endDate;
    @Column(nullable=false) private Integer numPeople;
    @Column(precision=12, scale=2) private BigDecimal budget;
    @Column(nullable=false) private String status; // DRAFT/PLANNING/CONFIRMED
}
