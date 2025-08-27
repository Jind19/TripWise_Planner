package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;


@Entity @Table(name = "trip_packing_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TripPackingItem extends BaseEntity {
    @ManyToOne(optional=false) private Trip trip;
    private Long templateItemId; // optional backref
    @Column(nullable=false) private String name;
    @Column(nullable=false) private Integer quantity; // expanded = qtyPerPerson * numPeople
    @Column(nullable=false) private boolean checked;
}
