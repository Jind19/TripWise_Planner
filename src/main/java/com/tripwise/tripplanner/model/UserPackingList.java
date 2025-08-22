package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPackingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tripId;    // link to Trip (from Trip entity)
    private Long userId;    // authenticated user

    @OneToMany(mappedBy = "userPackingList", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PackingListItem> items = new ArrayList<>();
}

