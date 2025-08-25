// repo/PackingTemplateRepository.java
package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.PackingTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PackingTemplateRepository extends JpaRepository<PackingTemplate, UUID> {
    Optional<PackingTemplate> findByNameIgnoreCase(String name);
}
