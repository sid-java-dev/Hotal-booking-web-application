package com.hotel_booking.service;

import com.hotel_booking.entity.Amenities;
import com.hotel_booking.payload.AmenitiesDto;

import java.util.List;

public interface AmenitiesService {
List<Amenities> addAmenities(AmenitiesDto dto);

    List<Amenities> getAmenities(List<Amenities> amenities);
}
