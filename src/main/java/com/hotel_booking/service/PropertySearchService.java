package com.hotel_booking.service;

import com.hotel_booking.entity.Property;
import com.hotel_booking.payload.SearchPropertyDto;

import java.util.List;


public interface PropertySearchService {
    SearchPropertyDto getPropertyById(long propertyId);

    List<SearchPropertyDto> findPropertyByPlace(String placeName);
}
