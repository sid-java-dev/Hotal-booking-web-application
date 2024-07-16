package com.hotel_booking.repository;

import com.hotel_booking.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
    @Query("select a FROM Amenities a WHERE a.amenityName IN:amenityNames")
    List<Amenities> findByAmenityName(@Param("amenityNames") List<String> amenities);
}
