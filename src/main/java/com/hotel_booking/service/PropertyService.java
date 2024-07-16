package com.hotel_booking.service;

import com.hotel_booking.entity.Property;
import com.hotel_booking.payload.PropertyDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyService {
    Property saveProperty(PropertyDto propertyDto);

    Property getPropertyById(Long id);

    List<Property> getPropertyByPlace(String placeName);
}
