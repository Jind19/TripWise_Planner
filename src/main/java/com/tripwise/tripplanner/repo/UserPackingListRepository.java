// repo/UserPackingListRepository.java
package com.tripwise.tripplanner.repo;

import com.tripwise.tripplanner.model.UserPackingList;
import com.tripwise.tripplanner.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserPackingListRepository extends JpaRepository<UserPackingList, UUID> {
    Optional<UserPackingList> findByTripAndUserId(Trip trip, String userId);
}
