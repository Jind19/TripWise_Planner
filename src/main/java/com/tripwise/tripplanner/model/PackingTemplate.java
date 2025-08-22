package com.tripwise.tripplanner.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "packing_templates")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class PackingTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name; // Beach, Jungle, Business

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TemplateItem> items = new ArrayList<>();
}
