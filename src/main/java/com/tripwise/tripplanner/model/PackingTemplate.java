package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@org.hibernate.annotations.Immutable
@Entity
@Table(name = "packing_templates" /* unique name constraint */)
public class PackingTemplate extends BaseEntity {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name; // Beach, Jungle, Business

    private String description;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TemplateItem> items = new ArrayList<>();
}
