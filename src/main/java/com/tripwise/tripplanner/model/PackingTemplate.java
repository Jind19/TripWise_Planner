package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity @Table(name = "packing_templates")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PackingTemplate extends BaseEntity {
    @Column(nullable=false, unique=true) private String name;
    private String description;
    @OneToMany(mappedBy="template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PackingTemplateItem> items;
    @Column(nullable=false) private boolean active;
    @Column(nullable=false) private int version; // bump on admin edits
}
