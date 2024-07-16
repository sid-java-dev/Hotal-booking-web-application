package com.hotel_booking.repository;

import com.hotel_booking.entity.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage,Long> {
    List<PropertyImage> getAllPropertyByPropertyId(long propertyId);
}
