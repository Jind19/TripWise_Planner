package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean packed;  // ✅ tick mark (true = packed, false = not packed)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_packing_list_id", nullable = false)  // explicit foreign key column
    private UserPackingList userPackingList;
}

