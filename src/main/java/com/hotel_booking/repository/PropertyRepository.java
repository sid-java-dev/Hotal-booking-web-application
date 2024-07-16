package com.hotel_booking.repository;

import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p From Property p Join p.location l join l.country c where l.locationName=:placeName OR c.countryName=:placeName ")
    List<Property> findPropertyByPlace(@Param("placeName")String placeName);


}