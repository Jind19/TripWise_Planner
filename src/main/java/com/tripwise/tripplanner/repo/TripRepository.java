package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByOrderByStartDateDesc();
}
