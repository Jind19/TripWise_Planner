package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.TripPackingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripPackingItemRepository extends JpaRepository<TripPackingItem, Long> {
    List<TripPackingItem> findByTripIdOrderByNameAsc(Long tripId);
}
