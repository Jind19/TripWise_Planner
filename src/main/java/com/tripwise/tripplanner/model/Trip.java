package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal; import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity @Table(name = "trips")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Trip extends BaseEntity {
    @Column(nullable=false) private String destination;
    @Column(nullable=false) private LocalDate startDate;
    @Column(nullable=false) private LocalDate endDate;
    @Column(nullable=false) private Integer numPeople;
    @Column(precision=12, scale=2) private BigDecimal budget;
    @Column(nullable=false) private String status = "PLANNED";

    @OneToMany(mappedBy = "trip",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<TripPackingItem> items = new ArrayList<>();

    // helper method if you want to safely add/remove
    public void addItem(TripPackingItem item) {
        items.add(item);
        item.setTrip(this);
    }

    public void removeItem(TripPackingItem item) {
        items.remove(item);
        item.setTrip(null);
    }
}
