package com.hotel_booking.payload;

import com.hotel_booking.entity.Amenities;
import com.hotel_booking.entity.Location;
import com.hotel_booking.entity.PropertyCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class PropertyDto {
    private Long id;
    private String propertyName;
    private Integer noOfBathrooms;
    private Integer noOfBeds;
    private Integer noOfBedrooms;
    private Integer noOfGuests;
    private Double nightlyPrice;
    private PropertyCategory propertyCategory;
    private Location location;
    private List<Amenities> amenities;
}
