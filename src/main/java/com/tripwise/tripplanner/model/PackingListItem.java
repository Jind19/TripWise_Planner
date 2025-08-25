package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "packing_list_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PackingListItem extends BaseEntity {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packing_list_id", nullable = false)
    private UserPackingList packingList;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean packed;

    private String notes;
}

