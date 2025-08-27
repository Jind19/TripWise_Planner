package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;


@Entity @Table(name = "packing_template_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PackingTemplateItem extends BaseEntity {
    @ManyToOne(optional=false) private PackingTemplate template;
    @Column(nullable=false) private String name;
    @Column(nullable=false) private Integer qtyPerPerson;
    private String notes;
}
