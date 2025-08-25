// repo/PackingListItemRepository.java
package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.PackingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PackingListItemRepository extends JpaRepository<PackingListItem, UUID> {}

