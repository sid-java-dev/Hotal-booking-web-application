package com.hotel_booking.payload;

import com.hotel_booking.entity.Review;
import lombok.Data;

import java.util.List;

@Data
public class ResponseReviewDto {
    private List<Review> reviewList;
    private double overallRating;
}
