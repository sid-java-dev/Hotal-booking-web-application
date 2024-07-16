package com.hotel_booking.payload;

import com.hotel_booking.entity.PropertyCategory;
import lombok.Data;

import java.util.List;
import java.util.Locale;

@Data
public class CategoryDto {
    private List<String> categories;
}
