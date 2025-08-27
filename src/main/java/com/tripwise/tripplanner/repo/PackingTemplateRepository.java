package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.PackingTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackingTemplateRepository extends JpaRepository<PackingTemplate, Long> {
    List<PackingTemplate> findByActiveTrueOrderByNameAsc();
}
