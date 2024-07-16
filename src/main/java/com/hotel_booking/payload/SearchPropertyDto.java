package com.hotel_booking.payload;

import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPropertyDto {
    private Property property;
    private List<PropertyImage> imageList;
}
