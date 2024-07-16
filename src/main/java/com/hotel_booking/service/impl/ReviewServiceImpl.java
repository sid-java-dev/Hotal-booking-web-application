package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.entity.Review;
import com.hotel_booking.exception.PropertyException;
import com.hotel_booking.exception.ReviewException;
import com.hotel_booking.payload.ResponseReviewDto;
import com.hotel_booking.payload.ReviewDto;
import com.hotel_booking.repository.ReviewRepository;
import com.hotel_booking.service.PropertyService;
import com.hotel_booking.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyService propertyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyService propertyService) {
        this.reviewRepository = reviewRepository;
        this.propertyService = propertyService;
    }

    @Override
    public Review addReview(Long propertyId, ReviewDto reviewDto, PropertyUser propertyUser) {
        Property property = propertyService.getPropertyById(propertyId);
        if (property != null) {
            Optional<Review> r = reviewRepository.findReviewByUser(property, propertyUser);
            if (r.isPresent()) {
                throw new ReviewException("Review already given for property");
            }
            Review review = new Review();
            review.setContent(reviewDto.getContent());
            review.setRating(reviewDto.getRating());
            review.setProperty(property);
            review.setPropertyUser(propertyUser);
            return reviewRepository.save(review);
        } else {
            throw new PropertyException("Property not found");
        }

    }

    @Override
    public List<Review> getReviewGivenByUser(Long propertyUserId) {
        List<Review> reviews = reviewRepository.findByPropertyUserId(propertyUserId);
        if (reviews.isEmpty()) {
            throw new ReviewException("User not given Review for any Properties");
        }
        return reviews;
    }

    @Override
    public ResponseReviewDto getAllReviewsForProperty(Long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        List<Review> reviewList = reviewRepository.findReviewForProperty(property);
        ResponseReviewDto dto = new ResponseReviewDto();
        if (!reviewList.isEmpty()) {
            int sum = reviewList.stream().mapToInt(Review::getRating).sum();
            double rating = (double) sum / reviewList.size();
            dto.setReviewList(reviewList);
            dto.setOverallRating(rating);
        } else {
            dto.setReviewList(Collections.emptyList());
            dto.setOverallRating(0.0); // or null, depending on the requirements
        }
        return dto;
    }
}
