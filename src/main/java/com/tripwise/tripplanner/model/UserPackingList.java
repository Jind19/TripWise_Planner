package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_packing_lists", indexes = {
        @Index(name = "idx_upl_trip_user", columnList = "trip_id,userId", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserPackingList extends BaseEntity {
    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    /** Snapshot of template name/version at clone time (optional, helpful for history) */
    @Column(nullable = true)
    private String templateNameSnapshot;

    @OneToMany(mappedBy = "packingList", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PackingListItem> items = new ArrayList<>();
}

