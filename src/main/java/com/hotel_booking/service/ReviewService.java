package com.hotel_booking.service;

import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.entity.Review;
import com.hotel_booking.payload.ResponseReviewDto;
import com.hotel_booking.payload.ReviewDto;

import java.util.List;

public interface ReviewService {
    Review addReview(Long propertyId, ReviewDto reviewDto, PropertyUser propertyUser);

    List<Review> getReviewGivenByUser(Long id);

    ResponseReviewDto getAllReviewsForProperty(Long propertyId);
}
