package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "template_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TemplateItem extends BaseEntity {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private PackingTemplate template;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int defaultQuantity;
}
