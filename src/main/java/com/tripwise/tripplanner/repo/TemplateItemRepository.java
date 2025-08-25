// repo/TemplateItemRepository.java
package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.TemplateItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemplateItemRepository extends JpaRepository<TemplateItem, UUID> {}

