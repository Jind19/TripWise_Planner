package com.tripwise.tripplanner.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Table(name = "trips")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinationCountry;

    private String destinationCity;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer peopleCount;
    private BigDecimal budget;

    @Column(nullable = false)
    private OffsetDateTime createdAt;




}
