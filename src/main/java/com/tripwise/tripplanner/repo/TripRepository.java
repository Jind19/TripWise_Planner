// repo/TripRepository.java
package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    List<Trip> findByUserId(String userId);
}

