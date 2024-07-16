package com.hotel_booking.payload;


import lombok.Data;

import java.util.List;
@Data
public class AmenitiesDto {
    private List<String> amenityNames;
}
