package com.hotel_booking.repository;

import com.hotel_booking.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByLocationName(String locationName);
}