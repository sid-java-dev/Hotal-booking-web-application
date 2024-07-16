package com.hotel_booking.payload;

import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Integer rating;
    private String content;
}
